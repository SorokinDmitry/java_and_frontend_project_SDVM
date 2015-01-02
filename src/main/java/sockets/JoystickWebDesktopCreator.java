package sockets;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;

/**
 * @author Dmitry
 */
public class JoystickWebDesktopCreator implements org.eclipse.jetty.websocket.servlet.WebSocketCreator {
    private JoystickWebSocketService joystickWebSocketService;

    public JoystickWebDesktopCreator(JoystickWebSocketService joystickWebSocketService) {
        this.joystickWebSocketService = joystickWebSocketService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        return new DesktopWebSocket(joystickWebSocketService);
    }
}
