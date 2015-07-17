package org.traccar.web;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.traccar.Context;
import org.traccar.model.Command;
import org.traccar.database.ActiveDevice;

public class CommandServlet extends BaseServlet {

    @Override
    protected boolean handle(String command, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        switch (command) {
            case "/send":
                send(req, resp);
                return true;
            case "/raw":
                raw(req, resp);
                return true;
            default:
                return false;
        }
    }

    public ActiveDevice getActiveDevice(long deviceId) {
        ActiveDevice activeDevice = Context.getConnectionManager().getActiveDevice(deviceId);
        if (activeDevice == null) {
            throw new RuntimeException("The device is not registered on the server");
        }
        return activeDevice;
    }

    private void send(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Command command = JsonConverter.objectFromJson(req.getReader(), new Command());
        getActiveDevice(command.getDeviceId()).write(command);
        sendResponse(resp.getWriter(), true);
    }

    private void raw(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        JsonObject json = Json.createReader(req.getReader()).readObject();
        long deviceId = json.getJsonNumber("deviceId").longValue();
        String command = json.getString("command");
        getActiveDevice(deviceId).write(command);
        sendResponse(resp.getWriter(), true);
    }
}
