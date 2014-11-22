package main;

import admin.*;
import base.AccountService;
import base.AccountServiceImpl;
import servlets.*;
import mechanics.GameMechanics;
import mechanics.GameMechanicsImpl;
import sockets.WebSocketService;
import sockets.WebSocketServiceImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.ResourceFactory;
import resources.ServerResources;

import javax.servlet.Servlet;

/**
 * Created by Dmitry
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ServerResources res = ResourceFactory.getInstance().getServerResources();
        System.out.append("Starting at port: ").append(String.valueOf(res.getPort())).append('\n');
        System.out.append("WebSocket ip: ").append(res.getIp()).append('\n');

        AccountService accountService = new AccountServiceImpl();
        WebSocketService webSocketService = new WebSocketServiceImpl();
        GameMechanics gameMechanics = new GameMechanicsImpl(webSocketService);

        Servlet signupServlet = new SignupServletImpl(accountService);
        Servlet signinServlet = new SigninServletImpl(accountService);
        Servlet mainServlet = new MainPageServletImpl(accountService);
        Servlet logoutServlet = new LogoutServletImpl(accountService);
        Servlet adminServlet = new AdminPageServlet(accountService);
        Servlet profileServlet = new ProfileServletImpl(accountService);
        Servlet gameServlet = new GameServlet(accountService);
        Servlet webSocketGameServlet = new WebSocketGameServlet(accountService, gameMechanics, webSocketService);
        Servlet webSocketMainServlet = new WebSocketMainServlet(accountService, gameMechanics, webSocketService);

        Server server = new Server(res.getPort());
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signupServlet),        "/auth/signup");
        context.addServlet(new ServletHolder(signinServlet),        "/auth/signin");
        context.addServlet(new ServletHolder(logoutServlet),        "/auth/logout");
        context.addServlet(new ServletHolder(mainServlet),          "/main");
        context.addServlet(new ServletHolder(profileServlet),       "/profile");
        context.addServlet(new ServletHolder(adminServlet),         "/admin");
        context.addServlet(new ServletHolder(gameServlet),          "/game");
        context.addServlet(new ServletHolder(webSocketGameServlet), "/gameplay");
        context.addServlet(new ServletHolder(webSocketMainServlet), "/socket");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        gameMechanics.run();
    }
}