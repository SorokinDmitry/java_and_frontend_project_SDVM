package sockets;

import base.AccountService;
import mechanics.GameMechanics;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * Created by Dmitry on 021 21.11.14.
 */
public class NewWebSocketCreator implements WebSocketCreator {
    private AccountService accountService;
    private WebSocketService webSocketService;

    public NewWebSocketCreator(AccountService accountService,
                                  GameMechanics gameMechanics,
                                  WebSocketService webSocketService) {
        this.accountService = accountService;
        this.webSocketService = webSocketService;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        String sessionId = req.getHttpServletRequest().getSession().getId();
        String name = accountService.getUserLoginBySessionId(sessionId);
        return new MainWebSocket(name, webSocketService);
    }
}