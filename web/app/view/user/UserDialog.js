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

Ext.define('Traccar.view.user.UserDialog', {
    extend: 'Ext.window.Window',
    xtype: 'user-dialog',

    requires: [
        'Traccar.view.user.UserDialogController'
    ],

    controller: 'userdialog',
    
    bodyPadding: styles.panel_padding,
    title: strings.settings_user,
    resizable: false,
    modal: true,
    
    items: {
        xtype: 'form',
        items: [{
            xtype: 'textfield',
            name: 'name',
            fieldLabel: strings.user_name
        }, {
            xtype: 'textfield',
            name: 'email',
            fieldLabel: strings.user_email,
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'password',
            fieldLabel: strings.user_password,
            inputType: 'password',
            allowBlank: false
        }, {
            xtype: 'checkboxfield',
            name: 'admin',
            fieldLabel: strings.user_admin,
            allowBlank: false,
            disabled: true,
            reference: 'adminField'
        }, {
            xtype: 'combobox',
            name: 'map',
            fieldLabel: strings.map_layer,
            store: 'MapTypes',
            displayField: 'name',
            valueField: 'key'
        }, {
            xtype: 'combobox',
            name: 'distanceUnit',
            fieldLabel: strings.settings_distance_unit,
            store: 'DistanceUnits',
            displayField: 'name',
            valueField: 'key'
        }, {
            xtype: 'combobox',
            name: 'speedUnit',
            fieldLabel: strings.settings_speed_unit,
            store: 'SpeedUnits',
            displayField: 'name',
            valueField: 'key'
        }, {
            xtype: 'numberfield',
            name: 'latitude',
            fieldLabel: strings.position_latitude
        }, {
            xtype: 'numberfield',
            name: 'longitude',
            fieldLabel: strings.position_longitude
        }, {
            xtype: 'numberfield',
            name: 'zoom',
            fieldLabel: strings.server_zoom
        }]
    },

    buttons: [{
        text: strings.shared_save,
        handler: 'onSaveClick'
    }, {
        text: strings.shared_cancel,
        handler: 'onCancelClick'
    }]

});
