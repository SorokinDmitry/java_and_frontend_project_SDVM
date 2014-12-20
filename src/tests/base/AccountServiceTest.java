package base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dataSets.UserDataSet;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    static private DatabaseService databaseService = new DBServiceImpl();
    static private AccountService accountService = new AccountServiceDBImpl(databaseService);
    UserDataSet[] userDataSets;

    @Before
    public void setUp() throws Exception {
        userDataSets = new dataSets.UserDataSet[2];
        Random rnd = new Random();
        for (Integer i = 0; i < userDataSets.length; i++) {
            StringBuilder username;
            do {
                username = new StringBuilder("user");
                Integer n = rnd.nextInt(10000);
                username.append(n.toString());
            } while (accountService.haveUser(username.toString()));
            String password = "pass";
            password += i.toString();
            String email = "email";
            email += i.toString() + "@mail.ru";
            userDataSets[i] = new dataSets.UserDataSet(username.toString(), password, email);
        }
    }

    @After
    public void tearDown() throws Exception {
        for (UserDataSet user : userDataSets) {
            if (accountService.haveUser(user.getLogin())) {
                databaseService.deleteUser(user.getLogin());
            }
        }
    }

    private UserDataSet[] addUsers(int cnt) {
        UserDataSet[] userProfiles = new UserDataSet[cnt];
        for (Integer i = 0; i < cnt; i++) {
            userProfiles[i] = this.userDataSets[i];
            String username = userProfiles[i].getLogin();
            String password = userProfiles[i].getPassword();
            String email = userProfiles[i].getEmail();
            accountService.addUser(username, password, email);
        }
        return userProfiles;
    }

    private Integer[] addSessions(UserDataSet[] userProfiles, int cnt) {
        Integer[] sessions = new Integer[cnt];
        Random rnd = new Random();
        for (Integer i = 0; i < cnt; i++) {
            sessions[i] = rnd.nextInt();
            accountService.addSession(sessions[i].toString(), userProfiles[i].getLogin());
        }
        return sessions;
    }

    @Test
    public void testCountOfUsers() throws Exception {
        UserDataSet user1 = userDataSets[0];
        UserDataSet user2 = userDataSets[1];
        int before = accountService.getCountOfUsers();
        accountService.addUser(user1.getLogin(),user1.getPassword(),user1.getEmail());
        assertEquals(1, accountService.getCountOfUsers() - before);
        accountService.addUser(user2.getLogin(),user2.getPassword(),user2.getEmail());
        assertEquals(2, accountService.getCountOfUsers() - before);
    }

    @Test
    public void testCountOfSessions() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        int before = accountService.getCountOfSessions();

        accountService.addSession("434",userProfiles[0].getLogin());
        assertEquals(1, accountService.getCountOfSessions() - before);

        accountService.addSession("476576",userProfiles[1].getLogin());
        assertEquals(2, accountService.getCountOfSessions() - before);
    }

    @Test
    public void testHaveUser() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        assertEquals(true, accountService.haveUser(userProfiles[0].getLogin()));
        assertEquals(true, accountService.haveUser(userProfiles[1].getLogin()));
    }

    @Test
    public void testAddUncorrectLoginUser() throws Exception {
        UserDataSet user1 = new UserDataSet("", "password", "email1");
        int before = accountService.getCountOfUsers();
        accountService.addUser(user1.getLogin(),user1.getPassword(),user1.getEmail());
        assertEquals(0, accountService.getCountOfUsers() - before);
    }

    @Test
    public void testAddUncorrectPassUser() throws Exception {
        UserDataSet user2 = new UserDataSet("sdgsdfhgsd", "", "email2");
        int before = accountService.getCountOfUsers();
        accountService.addUser(user2.getLogin(),user2.getPassword(),user2.getEmail());
        assertEquals(0, accountService.getCountOfUsers() - before);
    }

    @Test
    public void testAddUncorrectEmailUser() throws Exception {
        UserDataSet user3 = new UserDataSet("sadgdfnb", "pass", "");
        int before = accountService.getCountOfUsers();
        accountService.addUser(user3.getLogin(),user3.getPassword(),user3.getEmail());
        assertEquals(0, accountService.getCountOfUsers() - before);
    }


    @Test
    public void testAddRegisteredUserSession() throws Exception {
        UserDataSet[] userProfiles = addUsers(1);
        int before = accountService.getCountOfSessions();
        addSessions(userProfiles, 1);
        int after = accountService.getCountOfSessions();
        assertEquals(1, after - before);
    }

    @Test
    public void testAddUnregisteredUserSession() throws Exception {
        String unregisteredLogin = "logind677hjtf";
        assertEquals(false, accountService.haveUser(unregisteredLogin));
        int before = accountService.getCountOfSessions();
        accountService.addSession("1sddtyfuk", unregisteredLogin);
        int after = accountService.getCountOfSessions();
        assertEquals(0, after - before);
    }

    @Test
    public void testAddSameIdSession() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        int before = accountService.getCountOfSessions();
        accountService.addSession("1dfgg", userProfiles[0].getLogin());
        accountService.addSession("1dfgg", userProfiles[1].getLogin());
        int after = accountService.getCountOfSessions();
        assertEquals(1, after - before);
    }

    @Test
    public void testAddSameLoginSession() throws Exception {
        UserDataSet[] userProfiles = addUsers(1);
        int before = accountService.getCountOfSessions();
        accountService.addSession("1", userProfiles[0].getLogin());
        accountService.addSession("2", userProfiles[0].getLogin());
        int after = accountService.getCountOfSessions();
        assertEquals(1, after - before);
    }

    @Test
    public void testHaveSession() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        Integer[] sessions = addSessions(userProfiles, 2);
        assertEquals(true, accountService.haveSession(sessions[0].toString()));
        assertEquals(true, accountService.haveSession(sessions[1].toString()));
    }

    @Test
    public void testDeleteSession() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        Integer[] sessions = addSessions(userProfiles, 2);
        accountService.deleteSession(sessions[1].toString());
        assertEquals(false, accountService.haveSession(sessions[1].toString()));
    }

    @Test
    public void testCorrectPassword() throws Exception {
        UserDataSet[] userProfiles = addUsers(1);
        assertEquals(true, accountService.isPasswordCorrect(userProfiles[0].getLogin(),userProfiles[0].getPassword()));
    }

    @Test
    public void testUnCorrectPassword() throws Exception {
        UserDataSet[] userProfiles = addUsers(1);
        String uncorrectPass= userProfiles[0].getPassword()+"fsdfs";
        assertEquals(false, accountService.isPasswordCorrect(userProfiles[0].getLogin(),uncorrectPass));
    }

    @Test
    public void testGetUserEmailByCorrectSessionId() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        Integer[] sessions = addSessions(userProfiles, 2);
        assertEquals(userProfiles[0].getEmail(), accountService.getUserEmailBySessionId(sessions[0].toString()));
        assertEquals(userProfiles[1].getEmail(), accountService.getUserEmailBySessionId(sessions[1].toString()));
    }

    @Test
    public void testGetUserEmailByUncorrectSessionId() throws Exception {
        UserDataSet[] userProfiles = addUsers(1);
        Integer[] sessions = addSessions(userProfiles, 1);
        String uncorrectSessionId = sessions[0].toString()+"123";
        assertEquals(null, accountService.getUserEmailBySessionId(uncorrectSessionId));
    }

    @Test
    public void testGetUserLoginByCorrectSessionId() throws Exception {
        UserDataSet[] userProfiles = addUsers(2);
        Integer[] sessions = addSessions(userProfiles, 2);
        assertEquals(userProfiles[0].getLogin(), accountService.getUserLoginBySessionId(sessions[0].toString()));
        assertEquals(userProfiles[1].getLogin(), accountService.getUserLoginBySessionId(sessions[1].toString()));
    }

    @Test
    public void testGetUserLoginByUncorrectSessionId() throws Exception {
        UserDataSet[] userProfiles = addUsers(1);
        Integer[] sessions = addSessions(userProfiles, 1);
        String uncorrectSessionId = sessions[0].toString()+"123";
        assertEquals(null,accountService.getUserLoginBySessionId(uncorrectSessionId));
    }
}