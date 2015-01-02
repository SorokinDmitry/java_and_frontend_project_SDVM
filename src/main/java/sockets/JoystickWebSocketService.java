package sockets;


import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Created by Dmitry on 025 25.10.14.
 */
public class JoystickWebSocketService {
    private JoystickWebSocket joystickWebSocket;
    private ArrayList<DesktopWebSocket> desktopWebSockets = new ArrayList<>();

    public void setJoystick(JoystickWebSocket joystickWebSocket) {
        this.joystickWebSocket = joystickWebSocket;
    }

    public void addDesktop(DesktopWebSocket desktopkWebSocket) {
        desktopWebSockets.add(desktopkWebSocket);
    }

    public void sendMessage(String json) {
        for (int i=0; i < desktopWebSockets.size(); ++i) {
            desktopWebSockets.get(i).send(json);
        }
    }
}