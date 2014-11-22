package servlets;

import base.AccountService;
import mechanics.GameMechanics;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import sockets.CustomWebSocketCreator;
import sockets.WebSocketService;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Dmitry on 025 25.10.14.
 */

@WebServlet(name = "WebSocketGameServlet", urlPatterns = {"/game"})
public class WebSocketGameServlet extends WebSocketServlet {
    private final static int IDLE_TIME = 60 * 1000;
    private AccountService accountService;
    private GameMechanics gameMechanics;
    private WebSocketService webSocketService;


    public WebSocketGameServlet(AccountService accountService, GameMechanics gameMechanics, WebSocketService webSocketService) {
        this.accountService = accountService;
        this.gameMechanics = gameMechanics;
        this.webSocketService = webSocketService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(IDLE_TIME);
        factory.setCreator(new CustomWebSocketCreator(accountService, gameMechanics, webSocketService));
    }
}