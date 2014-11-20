package frontend;

import base.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.UserProfile;

import javax.servlet.http.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class MainPageServletImplTest {
    final private HttpServletRequest request = mock(HttpServletRequest.class);
    final private HttpServletResponse response = mock(HttpServletResponse.class);
    final private HttpSession httpSession = mock(HttpSession.class);
    final private AccountService accountService = mock(AccountService.class);
    private MainPageServletImpl mainPageServlet = new MainPageServletImpl(accountService);
    final StringWriter stringWrite = new StringWriter();
    final PrintWriter writer = new PrintWriter(stringWrite);

    private String sessionId = "sessionId";
    private String login = "login1";

    private void check(){
        verify(response).setContentType("text/html;charset=utf-8");
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Before
    public void setUp() throws Exception {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn(sessionId);
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGetHaveSessionTrue() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(true);
        UserProfile userProfile = mock(UserProfile.class);
        when(accountService.getUserProfileBySessionId(sessionId)).thenReturn(userProfile);
        when(userProfile.getLogin()).thenReturn(login);
        mainPageServlet.doGet(request,response);
        check();
        String st = stringWrite.toString();
        verify(accountService).getUserProfileBySessionId(sessionId);
        assertTrue(st.contains("<!DOCTYPE html>"));
        assertTrue(st.contains(login));
    }

    @Test
    public void testDoGetHaveSessionFalse() throws Exception {
        when(accountService.haveSession(sessionId)).thenReturn(false);
        mainPageServlet.doGet(request, response);
        check();
        verify(accountService,never()).getUserProfileBySessionId(sessionId);
        String st = stringWrite.toString();
        assertTrue(st.contains("<!DOCTYPE html>"));
        assertFalse(st.contains(login));
    }
}