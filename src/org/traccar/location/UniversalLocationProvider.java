/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
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
package org.traccar.location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.Response;
import org.traccar.Context;
import org.traccar.helper.Log;
import org.traccar.model.Network;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class UniversalLocationProvider implements LocationProvider {

    private String url;

    public UniversalLocationProvider(String url, String key) {
        this.url = url + "?key=" + key;
    }

    @Override
    public void getLocation(Network network, final LocationProviderCallback callback) {
        try {
            String request = Context.getObjectMapper().writeValueAsString(network);
            Context.getAsyncHttpClient().preparePost(url).setBody(request).execute(new AsyncCompletionHandler() {
                @Override
                public Object onCompleted(Response response) throws Exception {
                    try (JsonReader reader = Json.createReader(response.getResponseBodyAsStream())) {
                        JsonObject json = reader.readObject();
                        JsonObject location = json.getJsonObject("location");
                        callback.onSuccess(
                                location.getJsonNumber("lat").doubleValue(),
                                location.getJsonNumber("lng").doubleValue(),
                                json.getJsonNumber("accuracy").doubleValue());
                    }
                    return null;
                }

                @Override
                public void onThrowable(Throwable t) {
                    callback.onFailure();
                }
            });
        } catch (JsonProcessingException e) {
            Log.warning(e);
            callback.onFailure();
        }
    }

}
