package frontend;

import base.AccountService;
import org.junit.Before;
import org.junit.Test;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ProfileServletImplTest {
    final private static HttpServletRequest request = mock(HttpServletRequest.class);
    final private static HttpServletResponse response = mock(HttpServletResponse.class);
    final private static HttpSession httpSession = mock(HttpSession.class);
    final private static AccountService accountService = mock(AccountService.class);
    private MainPageServletImpl mainPageServlet = new MainPageServletImpl(accountService);
    final StringWriter stringWrite = new StringWriter();
    final PrintWriter writer = new PrintWriter(stringWrite);

    private void check(){
        verify(response).setContentType("text/html;charset=utf-8");
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Before
    public void setUp() throws Exception {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getId()).thenReturn("sessionId");
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGetHaveSessionTrue() throws Exception {

    }
}