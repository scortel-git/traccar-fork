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

Ext.define('Traccar.view.device.DeviceDialog', {
    extend: 'Ext.window.Window',

    requires: [
        'Traccar.view.device.DeviceDialogController'
    ],

    controller: 'deviceDialog',
    
    bodyPadding: styles.panelPadding,
    title: strings.deviceDialog,
    resizable: false,
    modal: true,
    
    items: {
        xtype: 'form',
        items: [{
            xtype: 'textfield',
            name: 'name',
            fieldLabel: strings.deviceName,
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'uniqueId',
            fieldLabel: strings.deviceIdentifier,
            allowBlank: false
        }]
    },

    buttons: [{
        text: strings.sharedSave,
        handler: 'onSaveClick'
    }, {
        text: strings.sharedCancel,
        handler: 'onCancelClick'
    }]

});
