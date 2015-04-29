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

Ext.Loader.setConfig({
    disableCaching: false
});

Ext.Loader.loadScript({

    url: function() {
        var urlParams = document.URL.split("?");
        var params = Ext.urlDecode(urlParams[urlParams.length - 1]);
        
        if (!params.lang) {
            return "Strings.js";
        } else {
            return "Strings-" + params.lang + ".js";
        }
    }(),

    onLoad: function() {

        Ext.application({
            name: 'Traccar',
            requires: [
                'Styles',
                'Login',
                'MainView'
            ],

            launch: function() {
                Ext.Ajax.request({
                    url: '/api/session',
                    success: function(response) {
                        var result = Ext.decode(response.responseText);
                        if (result.success && result.session) {
                            Ext.create('MainView', { renderTo: document.body });
                        } else {
                            Ext.create('Login').show();
                        }
                    }
                })
            }
        });
    }
});
