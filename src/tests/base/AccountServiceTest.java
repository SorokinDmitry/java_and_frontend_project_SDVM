package base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.UserProfile;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceImpl();
    }

    @Test
    public void testCountOfUsersCountOfSessions() throws Exception {
        UserProfile user1 = new UserProfile("login1", "password1", "email1");
        UserProfile user2 = new UserProfile("login2", "password2", "email2");
        int before = accountService.getCountOfUsers();
        accountService.addUser(user1);
        assertEquals(1, accountService.getCountOfUsers() - before);
        accountService.addUser(user2);
        assertEquals(2, accountService.getCountOfUsers() - before);
        before = accountService.getCountOfSessions();
        accountService.addSession("434","login1");
        assertEquals(1, accountService.getCountOfSessions() - before);
        accountService.addSession("476576","login2");
        assertEquals(2, accountService.getCountOfSessions() - before);
    }

    @Test
    public void testAddUserHaveUser() throws Exception {
        UserProfile user1 = new UserProfile("logindffhjtf", "password1", "email1");
        UserProfile user2 = new UserProfile("logindffhjtf", "password2", "email2");
        int before = accountService.getCountOfUsers();
        assertEquals(false, accountService.haveUser("logindffhjtf"));
        accountService.addUser(user1);
        accountService.addUser(user2);
        assertEquals(true, accountService.haveUser("logindffhjtf"));
        int after = accountService.getCountOfUsers();
        assertEquals(1, after - before);
    }

    @Test
    public void testAddUncorrectUser() throws Exception {
        UserProfile user1 = new UserProfile("", "password", "email1");
        UserProfile user2 = new UserProfile("sdgsdfhgsd", "", "email2");
        UserProfile user3 = new UserProfile("sadgdfnb", "pass", "");
        int before = accountService.getCountOfUsers();
        accountService.addUser(user1);
        assertEquals(0, accountService.getCountOfUsers() - before);
        accountService.addUser(user2);
        assertEquals(0, accountService.getCountOfUsers() - before);
        accountService.addUser(user3);
        assertEquals(0, accountService.getCountOfUsers() - before);
    }

    @Test
    public void testAddRegisteredUserSession() throws Exception {
        UserProfile user = new UserProfile("logindffhjtf", "password", "email");
        accountService.addUser(user);
        int before = accountService.getCountOfSessions();
        accountService.addSession("1", "logindffhjtf");
        int after = accountService.getCountOfSessions();
        assertEquals(1, after - before);
    }

    @Test
    public void testAddUnregisteredUserSession() throws Exception {
        assertEquals(false, accountService.haveUser("logind677hjtf"));
        int before = accountService.getCountOfSessions();
        accountService.addSession("1", "logind677hjtf");
        int after = accountService.getCountOfSessions();
        assertEquals(0, after - before);
    }

    @Test
    public void testAddSameIdSession() throws Exception {
        int before = accountService.getCountOfSessions();
        accountService.addSession("1", "login");
        accountService.addSession("1", "login2");
        int after = accountService.getCountOfSessions();
        assertEquals(1, after - before);
    }

    @Test
    public void testAddSameLoginSession() throws Exception {
        int before = accountService.getCountOfSessions();
        accountService.addSession("1", "login");
        accountService.addSession("2", "login");
        int after = accountService.getCountOfSessions();
        assertEquals(1, after - before);
    }

    @Test
    public void testHaveSessionDeleteSession() throws Exception {
        UserProfile user1 = new UserProfile("login1", "password1", "email1");
        UserProfile user2 = new UserProfile("login2", "password2", "email2");
        accountService.addUser(user1);
        accountService.addUser(user2);
        accountService.addSession("44634", "login1");
        accountService.addSession("68548", "login2");
        assertEquals(true, accountService.haveSession("44634"));
        assertEquals(true, accountService.haveSession("68548"));
        int before = accountService.getCountOfSessions();
        accountService.deleteSession("68548");
        int after = accountService.getCountOfSessions();
        assertEquals(-1, after - before);
        assertEquals(true, accountService.haveSession("44634"));
        assertEquals(false, accountService.haveSession("68548"));
    }

    @Test
    public void testCorrectPassword() throws Exception {
        UserProfile user = new UserProfile("login", "password", "email");
        accountService.addUser(user);
        assertEquals(false, accountService.isPasswordCorrect("login","pass"));
        assertEquals(true, accountService.isPasswordCorrect("login","password"));
    }

    @Test
    public void testGetUserProfile() throws Exception {
        UserProfile user1 = new UserProfile("login1", "password1", "email1");
        UserProfile user2 = new UserProfile("login2", "password2", "email2");
        accountService.addUser(user1);
        accountService.addUser(user2);
        accountService.addSession("esery546","login1");
        accountService.addSession("esesttrtu546","login2");
        assertEquals(user1,accountService.getUserProfileByLogin("login1"));
        assertEquals(user2,accountService.getUserProfileByLogin("login2"));
        assertEquals(null,accountService.getUserProfileByLogin("loginbnfgjnfgxcvcxbnm"));
        assertEquals(user1,accountService.getUserProfileBySessionId("esery546"));
        assertEquals(user2,accountService.getUserProfileBySessionId("esesttrtu546"));
        assertEquals(null,accountService.getUserProfileBySessionId("dfhjk.,mnbvghj"));
    }
}