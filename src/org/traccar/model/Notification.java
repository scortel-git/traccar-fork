/*
 * Copyright 2016 - 2018 Anton Tananaev (anton@traccar.org)
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
package org.traccar.model;

import java.util.HashSet;
import java.util.Set;

public class Notification extends ScheduledModel {

    private boolean always;

    public boolean getAlways() {
        return always;
    }

    public void setAlways(boolean always) {
        this.always = always;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private boolean web;

    public boolean getWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    private boolean mail;

    public boolean getMail() {
        return mail;
    }

    public void setMail(boolean mail) {
        this.mail = mail;
    }

    private boolean sms;

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public Set<String> getMethods() {
        final Set<String> set = new HashSet<>();
        if (web) {
            set.add("web");
        }
        if (mail) {
            set.add("mail");
        }
        if (sms) {
            set.add("sms");
        }
        return set;
    }

}
