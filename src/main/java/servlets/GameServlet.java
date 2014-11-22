package servlets;

import base.AccountService;
import resources.ResourceFactory;
import resources.ServerResources;
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


    public GameServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        // Получение логина активного пользователя
        String sessionId = request.getSession().getId();
        String login = null;
        if (accountService.haveSession(sessionId)) {
            login = accountService.getUserLoginBySessionId(sessionId);
            pageVariables.put("myName", login);
            pageVariables.put("server", ResourceFactory.getInstance().getServerResources().toString());
            response.getWriter().println(PageGenerator.getPage("game.html", pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect("/main");
        }
    }
}