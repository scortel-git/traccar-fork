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
(function () {
    'use strict';

    Ext.define('Traccar.view.UserController', {
        extend: 'Ext.app.ViewController',
        alias: 'controller.user',

        requires: [
            'Traccar.view.UserDialog'
        ],

        init: function () {
            Ext.getStore('Users').load();
        },

        onAddClick: function () {
            var user, dialog;
            user = Ext.create('Traccar.model.User'),
            dialog = Ext.create('Traccar.view.UserDialog');
            dialog.down('form').loadRecord(user);
            dialog.show();
        },

        onEditClick: function () {
            var user, dialog;
            user = this.getView().getSelectionModel().getSelection()[0];
            dialog = Ext.create('Traccar.view.UserDialog');
            dialog.down('form').loadRecord(user);
            dialog.show();
        },

        onRemoveClick: function () {
            var user = this.getView().getSelectionModel().getSelection()[0];
            Ext.Msg.show({
                title: Strings.settingsUser,
                message: Strings.sharedRemoveConfirm,
                buttons: Ext.Msg.YESNO,
                buttonText: {
                    yes: Strings.sharedRemove,
                    no: Strings.sharedCancel
                },
                fn: function (btn) {
                    if (btn === 'yes') {
                        var store = Ext.getStore('Users');
                        store.remove(user);
                        store.sync();
                    }
                }
            });
        },

        onDevicesClick: function () {
            // TODO show devices
            /*Ext.create('Ext.window.Window', {
             title: Strings.settingsUsers,
             width: Traccar.Style.windowWidth,
             height: Traccar.Style.windowHeight,
             layout: 'fit',
             modal: true,
             items: {
             xtype: 'userView'
             }
             }).show();*/
        },

        onSelectionChange: function (selected) {
            var disabled = selected.length > 0;
            this.lookupReference('userEditButton').setDisabled(disabled);
            this.lookupReference('userRemoveButton').setDisabled(disabled);
            this.lookupReference('userDevicesButton').setDisabled(disabled);
        }

    });

})();
