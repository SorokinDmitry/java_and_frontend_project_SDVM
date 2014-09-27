package main;

import admin.*;
import frontend.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

/**
 * @author v.chibrikov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length == 1) {
            String portString = args[0];
            port = Integer.valueOf(portString);
        } else {
            System.out.append("Use port as the first argument");
            System.exit(1);
        }

        System.out.append("Starting at port: ").append(String.valueOf(port)).append('\n');

        AccountService accountService = new AccountService();

        Servlet signupServlet = new SignupServlet(accountService);
        Servlet signinServlet = new SigninServlet(accountService);
        Servlet mainServlet = new MainPageServlet(accountService);
        Servlet logoutServlet = new LogoutServlet(accountService);
        Servlet adminServlet = new AdminPageServlet(accountService);
        Servlet profileServlet = new ProfileServlet(accountService);

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signupServlet), "/auth/signup");
        context.addServlet(new ServletHolder(signinServlet), "/auth/signin");
        context.addServlet(new ServletHolder(logoutServlet), "/auth/logout");
        context.addServlet(new ServletHolder(mainServlet),   "/main");
        context.addServlet(new ServletHolder(profileServlet), "/profile");
        context.addServlet(new ServletHolder(adminServlet),   "/admin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}