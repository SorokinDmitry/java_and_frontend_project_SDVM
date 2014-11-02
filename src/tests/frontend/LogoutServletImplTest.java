package frontend;

import base.AccountService;
import org.junit.Before;
import org.junit.Test;
import utils.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static org.junit.Assert.*;

public class LogoutServletImplTest {
    static class AccountService1 implements AccountService {
        public void addUser(UserProfile user){}
        public void addSession(String id, String login){}
        public int getCountOfUsers(){return 1;}
        public int getCountOfSessions(){return 2;}
        public void deleteSession(String id){}
        public boolean haveSession(String id){return true;}
        public boolean haveUser(String login){return true;}
        public boolean isPasswordCorrect(String login, String password){return true;}
        public UserProfile getUserProfileByLogin(String login){return new UserProfile("login", "password", "email");}
        public UserProfile getUserProfileBySessionId(String id){return new UserProfile("login", "password", "email");}
    }
    static class AccountService2 extends AccountService1 {
        @Override
        public boolean haveSession(String id){return false;}
    }
    private HttpServlet logoutServlet;

    @Test
    public void testDoPostHaveSessionTrue() throws Exception {
        logoutServlet = new LogoutServletImpl(new AccountService1());
        //HttpServletRequest request = new HttpServletRequestWrapper()
    }

    @Test
    public void testDoPostHaveSessionFalse() throws Exception {
        logoutServlet = new LogoutServletImpl(new AccountService2());
    }
}