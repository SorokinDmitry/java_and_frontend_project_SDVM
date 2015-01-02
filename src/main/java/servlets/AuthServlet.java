package servlets;

import base.AccountService;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dmitry on 027 27.12.14.
 */
public class AuthServlet extends HttpServlet {
    private AccountService accountService;

    public AuthServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        // Получение логина активного пользователя
        String sessionId = request.getSession().getId();

        if (accountService.haveSession(sessionId)) {
            String login = accountService.getUserLoginBySessionId(sessionId);
            JSONObject json = new JSONObject();
            json.put("login", login);
            response.setContentType("application/json");
            response.getWriter().println(json.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}