/*
 * Copyright 2016 Anton Tananaev (anton.tananaev@gmail.com)
 * Copyright 2016 Andrey Kunitsyn (abyss@fox5.ru)
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

Ext.define('Traccar.view.ServerDialogController', {
    extend: 'Traccar.view.BaseEditDialogController',
    alias: 'controller.serverEditDialog',

    config: {
        listen: {
            controller: {
                '*': {
                    setCenterFromMap: 'setCenterFromMap'
                }
            }
        }
    },

    getFromMap: function (button) {
        this.fireEvent('getMapCenter');
    },

    setCenterFromMap: function (lat, lon, zoom) {
        var mapCenter = this.lookupReference('mapCenter');
        mapCenter.down('numberfield[name="latitude"]').setValue(lat);
        mapCenter.down('numberfield[name="longitude"]').setValue(lon);
        mapCenter.down('numberfield[name="zoom"]').setValue(zoom);
    }
});
