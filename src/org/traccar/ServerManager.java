/*
 * Copyright 2012 - 2016 Anton Tananaev (anton.tananaev@gmail.com)
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
package org.traccar;

import org.traccar.model.SupportedCommand;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ServerManager {

    private final Map<String, BaseProtocol> protocols = new HashMap<>();
    private final List<TrackerServer> serverList = new LinkedList<>();

    public ServerManager() throws Exception {

        List<String> names = new LinkedList<>();
        String packageName = "org.traccar.protocol";
        String packagePath = packageName.replace('.', '/');
        URL packageUrl = Thread.currentThread().getContextClassLoader().getResource(packagePath);

        if (packageUrl.getProtocol().equals("jar")) {
            String jarFileName = URLDecoder.decode(packageUrl.getFile(), StandardCharsets.UTF_8.name());
            try (JarFile jf = new JarFile(jarFileName.substring(5, jarFileName.indexOf("!")))) {
                Enumeration<JarEntry> jarEntries = jf.entries();
                while (jarEntries.hasMoreElements()) {
                    String entryName = jarEntries.nextElement().getName();
                    if (entryName.startsWith(packagePath) && entryName.length() > packagePath.length() + 5) {
                        names.add(entryName.substring(packagePath.length() + 1, entryName.lastIndexOf('.')));
                    }
                }
            }
        } else {
            File folder = new File(new URI(packageUrl.toString()));
            File[] files = folder.listFiles();
            if (files != null) {
                for (File actual: files) {
                    String entryName = actual.getName();
                    names.add(entryName.substring(0, entryName.lastIndexOf('.')));
                }
            }
        }

        for (String name : names) {
            Class protocolClass = Class.forName(packageName + '.' + name);
            if (BaseProtocol.class.isAssignableFrom(protocolClass)) {
                BaseProtocol baseProtocol = (BaseProtocol) protocolClass.newInstance();
                protocols.put(baseProtocol.getName(), baseProtocol);
                initProtocolServer(baseProtocol);
            }
        }
    }

    public void start() {
        for (Object server: serverList) {
            ((TrackerServer) server).start();
        }
    }

    public void stop() {
        for (Object server: serverList) {
            ((TrackerServer) server).stop();
        }

        // Release resources
        GlobalChannelFactory.release();
        GlobalTimer.release();
    }

    private void initProtocolServer(final Protocol protocol) {
        if (Context.getConfig().hasKey(protocol.getName() + ".port")) {
            protocol.initTrackerServers(serverList);
        }
    }

    public Collection<SupportedCommand> getProtocolSuppportedCommands(String protocol) {
        ArrayList<SupportedCommand> result = new ArrayList<>();

        if (protocol != null) {
            BaseProtocol baseProtocol = protocols.get(protocol);
            for (String commandKey : baseProtocol.getSupportedCommands()) {
                SupportedCommand supportedCommand = new SupportedCommand();
                supportedCommand.setKey(commandKey);
                supportedCommand.setName(commandKey);
                result.add(supportedCommand);
            }
        }

        return result;
    }
}
