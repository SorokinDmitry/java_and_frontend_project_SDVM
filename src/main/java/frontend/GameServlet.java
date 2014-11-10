package frontend;

import base.AccountService;
import mechanics.GameMechanics;
import utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmitry on 010 10.11.14.
 */
public class GameServlet extends HttpServlet {
    private AccountService accountService;
    private int port;


    public GameServlet(AccountService accountService, int port) {
        this.accountService = accountService;
        this.port = port;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        // Получение логина активного пользователя
        String sessionId = request.getSession().getId();
        String login = null;
        if (accountService.haveSession(sessionId)) {
            login = accountService.getUserProfileBySessionId(sessionId).getLogin();
            pageVariables.put("myName", login);
            pageVariables.put("port", port);
            response.getWriter().println(PageGenerator.getPage("game.html", pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect("/main");
        }
    }
}