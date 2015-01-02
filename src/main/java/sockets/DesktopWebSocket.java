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
public class DesktopWebSocket {
    private JoystickWebSocketService joystickWebSocketService;
    private Session session;

    public DesktopWebSocket(JoystickWebSocketService joystickWebSocketService) {
        this.joystickWebSocketService = joystickWebSocketService;
    }

    @OnWebSocketMessage
    public void onMessage(String data) throws JSONException {
        org.json.JSONObject jsonObject = new org.json.JSONObject(data);
        System.out.println(data);
        //jsonObject.getString("action");
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        setSession(session);
        joystickWebSocketService.addDesktop(this);
        System.out.print("WebSocketConnect");
    }

    public void send(String json) {
        try {
            session.getRemote().sendString(json);
        } catch (Exception e) {
            System.out.print(e.toString());
        }
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
