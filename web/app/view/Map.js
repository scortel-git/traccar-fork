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

Ext.define('Traccar.view.Map', {
    extend: 'Ext.form.Panel',
    xtype: 'mapView',

    requires: [
        'Traccar.view.MapController'
    ],

    controller: 'map',

    title: Strings.mapTitle,
    layout: 'fit',

    listeners: {
        afterrender: function () {
            var user, server, layer, type, bingKey, vectorLayer, lat, lon, zoom;

            user = Traccar.app.getUser();
            server = Traccar.app.getServer();

            type = user.get('map') || server.get('map');
            bingKey = server.get('bingKey');

            if (type === 'custom') {
                layer = new ol.layer.Tile({
                    source: new ol.source.XYZ({
                        url: server.get('mapUrl')
                    })
                });
            } else if (type === 'bingRoad') {
                layer = new ol.layer.Tile({
                    source: new ol.source.BingMaps({
                        key: bingKey,
                        imagerySet: 'Road'
                    })
                });
            } else if (type === 'bingAerial') {
                layer = new ol.layer.Tile({
                    source: new ol.source.BingMaps({
                        key: bingKey,
                        imagerySet: 'Aerial'
                    })
                });
            } else {
                layer = new ol.layer.Tile({
                    source: new ol.source.OSM({})
                });
            }

            this.vectorSource = new ol.source.Vector({});
            vectorLayer = new ol.layer.Vector({
                source: this.vectorSource
            });

            lat = user.get('latitude') || server.get('latitude') || Traccar.Style.mapDefaultLat;
            lon = user.get('longitude') || server.get('longitude') || Traccar.Style.mapDefaultLon;
            zoom = user.get('zoom') || server.get('zoom') || Traccar.Style.mapDefaultZoom;

            this.mapView = new ol.View({
                center: ol.proj.fromLonLat([lon, lat]),
                zoom: zoom,
                maxZoom: Traccar.Style.mapMaxZoom
            });

            this.map = new ol.Map({
                target: this.body.dom.id,
                layers: [layer, vectorLayer],
                view: this.mapView
            });
        },

        resize: function () {
            this.map.updateSize();
        }
    }
});
