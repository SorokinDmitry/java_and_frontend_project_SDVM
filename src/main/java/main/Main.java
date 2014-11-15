package main;

import admin.*;
import base.AccountService;
import base.AccountServiceImpl;
import frontend.*;
import mechanics.GameMechanics;
import mechanics.GameMechanicsImpl;
import frontend.WebSocketService;
import frontend.WebSocketServiceImpl;
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
        ResourceFactory.getInstance();                                      // Создание фабрики
        ServerResources res = ResourceFactory.getInstance().getServerResources();

        int port = res.getPort();
        /*if (args.length == 1) {
            String portString = args[0];
            port = Integer.valueOf(portString);
        } else {
            System.out.append("Use port as the first argument");
            System.exit(1);
        }*/

        System.out.append("Starting at port: ").append(String.valueOf(port)).append('\n');

        AccountService accountService = new AccountServiceImpl();
        WebSocketService webSocketService = new WebSocketServiceImpl();
        GameMechanics gameMechanics = new GameMechanicsImpl(webSocketService);

        Servlet signupServlet = new SignupServletImpl(accountService);
        Servlet signinServlet = new SigninServletImpl(accountService);
        Servlet mainServlet = new MainPageServletImpl(accountService);
        Servlet logoutServlet = new LogoutServletImpl(accountService);
        Servlet adminServlet = new AdminPageServlet(accountService);
        Servlet profileServlet = new ProfileServletImpl(accountService);
        Servlet gameServlet = new GameServlet(accountService, port);
        Servlet webSocketGameServlet = new WebSocketGameServlet(accountService, port, gameMechanics, webSocketService);

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signupServlet),        "/auth/signup");
        context.addServlet(new ServletHolder(signinServlet),        "/auth/signin");
        context.addServlet(new ServletHolder(logoutServlet),        "/auth/logout");
        context.addServlet(new ServletHolder(mainServlet),          "/main");
        context.addServlet(new ServletHolder(profileServlet),       "/profile");
        context.addServlet(new ServletHolder(adminServlet),         "/admin");
        context.addServlet(new ServletHolder(gameServlet),          "/game");
        context.addServlet(new ServletHolder(webSocketGameServlet), "/gameplay");

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