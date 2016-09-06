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

Ext.define('Traccar.view.ServerDialog', {
    extend: 'Traccar.view.BaseEditDialog',

    requires: [
        'Traccar.view.ServerDialogController'
    ],

    controller: 'serverEditDialog',
    title: Strings.serverTitle,

    items: {
        xtype: 'form',
        items: [{
            xtype: 'checkboxfield',
            name: 'registration',
            fieldLabel: Strings.serverRegistration,
            allowBlank: false
        }, {
            xtype: 'checkboxfield',
            name: 'readonly',
            fieldLabel: Strings.serverReadonly,
            allowBlank: false
        }, {
            xtype: 'combobox',
            name: 'map',
            fieldLabel: Strings.mapLayer,
            store: 'MapTypes',
            displayField: 'name',
            valueField: 'key'
        }, {
            xtype: 'textfield',
            name: 'bingKey',
            fieldLabel: Strings.mapBingKey
        }, {
            xtype: 'textfield',
            name: 'mapUrl',
            fieldLabel: Strings.mapCustom
        }, {
            xtype: 'combobox',
            name: 'distanceUnit',
            fieldLabel: Strings.sharedDistance,
            store: 'DistanceUnits',
            displayField: 'name',
            valueField: 'key'
        }, {
            xtype: 'combobox',
            name: 'speedUnit',
            fieldLabel: Strings.settingsSpeedUnit,
            store: 'SpeedUnits',
            displayField: 'name',
            valueField: 'key'
        }, {
            xtype: 'fieldcontainer',
            reference: 'mapCenter',
            defaultType: 'numberfield',
            items: [{
                name: 'latitude',
                fieldLabel: Strings.positionLatitude,
                decimalPrecision: Traccar.Style.coordinatePrecision
            }, {
                name: 'longitude',
                fieldLabel: Strings.positionLongitude,
                decimalPrecision: Traccar.Style.coordinatePrecision
            }, {
                name: 'zoom',
                fieldLabel: Strings.serverZoom
            }]
        }, {
            xtype: 'checkboxfield',
            name: 'twelveHourFormat',
            fieldLabel: Strings.settingsTwelveHourFormat,
            allowBlank: false
        }]
    },

    buttons: [{
        text: Strings.sharedAttributes,
        handler: 'showAttributesView'
    }, {
        glyph: 'xf276@FontAwesome',
        minWidth: 0,
        handler: 'getFromMap',
        tooltip: Strings.sharedGetFromMap,
        tooltipType: 'title'
    }, {
        xtype: 'tbfill'
    }, {
        text: Strings.sharedSave,
        handler: 'onSaveClick'
    }, {
        text: Strings.sharedCancel,
        handler: 'closeView'
    }]
});
