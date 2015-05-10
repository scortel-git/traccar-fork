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

Ext.define('Traccar.view.login.Register', {
    extend: 'Ext.window.Window',
    
    requires: [
        'Traccar.view.login.RegisterController'
    ],
    
    controller: 'register',

    bodyPadding: styles.panel_padding,
    title: strings.login_register,
    resizable: false,
    modal: true,
    
    items: {
        xtype: 'form',
        reference: 'form',
        jsonSubmit: true,

        items: [{
            xtype: 'textfield',
            name: 'name',
            fieldLabel: strings.login_name,
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'email',
            fieldLabel: strings.login_email,
            vtype: 'email',
            allowBlank: false
        }, {
            xtype: 'textfield',
            name: 'password',
            fieldLabel: strings.login_password,
            inputType: 'password',
            allowBlank: false
        }]
    },

    buttons: [{
        text: strings.dialog_save,
        handler: 'onCreateClick'
    }, {
        text: strings.dialog_cancel,
        handler: 'closeView'
    }]

});
