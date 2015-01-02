package servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import resources.ResourceFactory;
import resources.ServerResources;
import sockets.JoystickWebSocketCreator;
import sockets.JoystickWebSocketService;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Dmitry on 025 25.10.14.
 */

@WebServlet(name = "WebSocketJoysticServlet", urlPatterns = {"/joystic"})
public class WebSocketJoysticServlet extends WebSocketServlet {
    private JoystickWebSocketService joystickWebSocketService;


    public WebSocketJoysticServlet(JoystickWebSocketService joystickWebSocketService) {
        this.joystickWebSocketService = joystickWebSocketService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        ServerResources res = ResourceFactory.getInstance().getServerResources();
        factory.getPolicy().setIdleTimeout(res.getIdleTime());
        factory.setCreator(new JoystickWebSocketCreator(joystickWebSocketService));
    }
}