package sockets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONException;

/**
 * Created by Dmitry on 025 25.10.14.
 */

@WebSocket
public class JoystickWebSocket {
    private JoystickWebSocketService joystickWebSocketService;
    private Session session;

    public JoystickWebSocket(JoystickWebSocketService joystickWebSocketService) {
        this.joystickWebSocketService = joystickWebSocketService;
    }

    @OnWebSocketMessage
    public void onMessage(String data) throws JSONException {
        org.json.JSONObject jsonObject = new org.json.JSONObject(data);
        //System.out.println(data);
        //jsonObject.getString("action");
        joystickWebSocketService.sendMessage(data);
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        setSession(session);
        joystickWebSocketService.setJoystick(this);
        System.out.print("WebSocketConnect");
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    }
}

