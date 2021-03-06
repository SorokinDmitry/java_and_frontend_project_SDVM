package frontend;

import base.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import servlets.SignupServletImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class SignupServletImplTest extends AssertResponse {
    final private HttpServletRequest request = mock(HttpServletRequest.class);
    final private HttpServletResponse response = mock(HttpServletResponse.class);
    final private HttpSession httpSession = mock(HttpSession.class);
    final private AccountService accountService = mock(AccountService.class);
    private SignupServletImpl signupServlet= new SignupServletImpl(accountService);
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
        when(request.getParameter("email")).thenReturn(email);
    }

    @Test
    public void testDoGetHaveSessionFalse() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(false);
        signupServlet.doGet(request, response);
        assertResponseOk(response);
        String st = stringWrite.toString();
        Assert.assertTrue(st.contains("Registration Form"));
    }

    @Test
    public void testDoGetHaveSessionTrue() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(true);
        signupServlet.doGet(request,response);
        //check();
        verify(response).sendRedirect("/main");
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        when(accountService.haveUser(login)).thenReturn(false);
        signupServlet.doPost(request, response);
        //verify(accountService).addUser(login, password, email);
        verify(accountService).addSession(sessionId, login);
        assertResponseOk(response);
        verify(response).sendRedirect("/main");
    }

    @Test
    public void testDoPostHaveUser() throws Exception {
        when(accountService.haveUser(login)).thenReturn(true);
        signupServlet.doPost(request, response);
        String st = stringWrite.toString();
        // System.out.append(st);
        Assert.assertTrue(st.contains("uncorrect login"));
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void testDoPostEmptyLogin() throws Exception {
        when(request.getParameter("login")).thenReturn("");
        signupServlet.doPost(request, response);
        String st = stringWrite.toString();
        // System.out.append(st);
        Assert.assertTrue(st.contains("uncorrect login"));
        verify(response).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void testDoPostHaveSessionTrue() throws Exception {
        when(request.getSession().getId()).thenReturn("id1");
        when(accountService.haveSession("id1")).thenReturn(true);
        signupServlet.doPost(request, response);
        verify(accountService, atLeastOnce()).deleteSession("id1");
    }
}