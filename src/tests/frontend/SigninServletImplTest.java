package frontend;

import base.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import servlets.SigninServletImpl;

import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.mockito.Mockito.*;

public class SigninServletImplTest extends AssertResponse {
    final private HttpServletRequest request = mock(HttpServletRequest.class);
    final private HttpServletResponse response = mock(HttpServletResponse.class);
    final private HttpSession httpSession = mock(HttpSession.class);
    final private AccountService accountService = mock(AccountService.class);
    private SigninServletImpl signinServlet= new SigninServletImpl(accountService);
    final StringWriter stringWrite = new StringWriter();
    final PrintWriter writer = new PrintWriter(stringWrite);

    private String sessionId = "sessionId";
    private String login = "login1";
    private String password = "password";
    private String email = "email@mail.ru";


    @Before
    public void setUp() throws Exception {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionId);
        when(response.getWriter()).thenReturn(writer);
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
    }

    @Test
    public void testDoGetHaveSessionFalse() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(false);
        signinServlet.doGet(request,response);
        assertResponseOk(response);
        String st = stringWrite.toString();
        Assert.assertTrue(st.contains("Enter yor login and password"));
    }

    @Test
    public void testDoGetHaveSessionTrue() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(true);
        signinServlet.doGet(request,response);
        //check();
        verify(response).sendRedirect("/main");
    }

    @Test
    public void testDoPostSuccessHaveSessionFalse() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(false);
        when(accountService.haveUser(login)).thenReturn(true);
        when(accountService.isPasswordCorrect(login, password)).thenReturn(true);
        signinServlet.doPost(request, response);
        verify(accountService, never()).deleteSession(sessionId);
        verify(accountService).addSession(sessionId, login);
        verify(response).setStatus(200);
        verify(response).sendRedirect("/main");
    }

    @Test
    public void testDoPostSuccessHaveSessionTrue() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(true);
        when(accountService.haveUser(login)).thenReturn(true);
        when(accountService.isPasswordCorrect(login, password)).thenReturn(true);
        signinServlet.doPost(request, response);
        verify(accountService).deleteSession(sessionId);
        verify(accountService).addSession(sessionId, login);
        verify(response).setStatus(200);
        verify(response).sendRedirect("/main");
    }

    @Test
    public void testDoPostHaveUserFalse() throws Exception {
        when(accountService.haveUser(login)).thenReturn(false);
        signinServlet.doPost(request, response);
        String st = stringWrite.toString();
       // System.out.append(st);
        Assert.assertTrue(st.contains("login does not exist"));
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void testDoPostUncorrectPass() throws Exception {
        when(accountService.haveUser(login)).thenReturn(true);
        when(accountService.isPasswordCorrect(login, "password")).thenReturn(false);
        signinServlet.doPost(request, response);
        String st = stringWrite.toString();
       // System.out.append(st);
        Assert.assertTrue(st.contains("wrong password"));
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}