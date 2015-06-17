/*
 * Copyright 2015 Anton Tananaev (anton.tananaev@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

Ext.define('Traccar.view.map.MapController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.map',
    
    config: {
        listen: {
            controller: {
                '*': {
                    reportShow: 'reportShow',
                    reportClear: 'reportClear',
                    selectDevice: 'selectDevice',
                    selectReport: 'selectReport'
                }
            }
        }
    },
    
    init: function() {
        this.liveData = {};
        this.update();
    },
    
    update: function() {
        Ext.Ajax.request({
            scope: this,
            url: '/api/async',
            success: function(response) {
                var data = Ext.decode(response.responseText).data;
                
                var i;
                for (i = 0; i < data.length; i++) {
                    
                    var geometry = new ol.geom.Point(ol.proj.fromLonLat([
                        data[i].longitude,
                        data[i].latitude
                    ]));
                    
                    if (data[i].deviceId in this.liveData) {
                        this.liveData[data[i].deviceId].setGeometry(geometry);
                    } else {
                        var marker = new ol.Feature({
                            geometry: geometry
                        });
                        marker.setStyle(new ol.style.Style({
                            image: new ol.style.Circle({
                                radius: 40,
                                fill: new ol.style.Fill({
                                    color: 'rgba(150,150,200,0.6)'
                                }),
                                stroke: new ol.style.Stroke({
                                    color: 'rgba(20,30,100,0.8)',
                                    width: 3
                                })
                            })
                        }));
                        this.getView().vectorSource.addFeature(marker);
                        this.liveData[data[i].deviceId] = marker;
                    }
                }
                
                this.update();
            },
            failure: function() {
                // TODO: error
            }
        });
    },

    getLineStyle: function(color) {
        return new ol.style.Style({
            /*fill: new ol.style.Fill({
                color: color
            }),
            stroke: new ol.style.Stroke({
                color: color,
                width: styles.map_route_width
            }),*/
            image: new ol.style.Circle({
                radius: 40,
                fill: new ol.style.Fill({
                    color: 'rgba(150,150,200,0.6)'
                }),
                stroke: new ol.style.Stroke({
                    color: 'rgba(20,30,100,0.8)',
                    width: 3
                })
            })
        });
    },

    getMarkerStyle: function(color) {
        return new ol.style.Style({
            text: new ol.style.Text({
                text: '\uf041',
                font: 'normal 32px FontAwesome',
                textBaseline: 'Bottom',
                fill: new ol.style.Fill({
                    color: color
                }),
                stroke: new ol.style.Stroke({
                    color: 'black',
                    width: 2
                })
            })
        });
    },
    
    reportShow: function() {
        var vectorSource = this.getView().vectorSource;

        var data = Ext.getStore('Positions').getData().clone();
        data.sort('fixTime');
        
        var index;
        var positions = [];
        for (index = 0; index < data.getCount(); index++) {
            positions.push(ol.proj.fromLonLat([
                data.getAt(index).data.longitude,
                data.getAt(index).data.latitude
            ]));
        }

        this.reportRoute = new ol.Feature({
            geometry: new ol.geom.LineString(positions),
            style: this.getLineStyle(styles.map_report_route)
        });
        
        this.reportStart = new ol.Feature({
            geometry: new ol.geom.Point(positions[0]),
            style: this.getLineStyle(styles.map_report_route)
        });
        
        this.reportFinish = new ol.Feature({
            geometry: new ol.geom.Point(positions[positions.length - 1]),
            style: this.getLineStyle(styles.map_report_route)
        });

        vectorSource.addFeature(this.reportRoute);
        vectorSource.addFeature(this.reportStart);
        vectorSource.addFeature(this.reportFinish);
    },

    reportClear: function() {
        var vectorSource = this.getView().vectorSource;
        
        vectorSource.removeFeature(this.reportRoute);
        vectorSource.removeFeature(this.reportStart);
        vectorSource.addFeature(this.reportFinish);
    },

    selectPosition: function(position) {
        if (this.currentPosition === undefined) {
            this.currentPosition = new ol.Feature({
                style: this.getLineStyle(styles.map_report_route)
            });
            this.getView().vectorSource.addFeature(this.currentPosition);
        }

        var point = ol.proj.fromLonLat([
            position.get('longitude'), position.get('latitude')
        ]);

        this.currentPosition.setGeometry(new ol.geom.Point(point));

        var pan = ol.animation.pan({
            duration: styles.map_delay,
            source: this.getView().mapView.getCenter()
        });
        this.getView().map.beforeRender(pan);
        this.getView().mapView.setCenter(point);
    },

    selectDevice: function(device) {
        console.log(device); // DELME
    },

    selectReport: function(position) {
        this.selectPosition(position);
    }

});
