package base;

import org.junit.Before;
import org.junit.Test;
import dataSets.UserDataSet;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    final private DatabaseService databaseService = mock(DatabaseService.class);
    private AccountService accountService = new AccountServiceDBImpl(databaseService);
    private UserDataSet[] userDataSets;
    private String login = "login";
    private String password = "password";
    private String email = "e@mail.ru";


    @Before
    public void setUp() throws Exception {
        when(databaseService.checkUserRegistered(anyString())).thenReturn(false);
        userDataSets = new dataSets.UserDataSet[2];
        for (Integer i = 0; i < userDataSets.length; i++) {
            String username = "user" + i.toString();
            String password = "pass" + i.toString();
            String email = "email" + i.toString() + "@mail.ru";
            userDataSets[i] = new dataSets.UserDataSet(username, password, email);
        }
    }

    private UserDataSet[] addUsers(int cnt) {
        UserDataSet[] userProfiles = new UserDataSet[cnt];
        for (Integer i = 0; i < cnt; i++) {
            userProfiles[i] = userDataSets[i];
            String username = userProfiles[i].getLogin();
            when(databaseService.getUser(username)).thenReturn(userProfiles[i]);
            when(databaseService.checkUserRegistered(username)).thenReturn(true);
        }
        when(databaseService.countOfUsers()).thenReturn((long)cnt);
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
    public void testHaveUserTrue() throws Exception {
        when(databaseService.checkUserRegistered(login)).thenReturn(true);
        assertEquals(true, accountService.haveUser(login));
        verify(databaseService).checkUserRegistered(login);
    }

    @Test
    public void testHaveUserFalse() throws Exception {
        when(databaseService.checkUserRegistered(login)).thenReturn(false);
        assertEquals(false, accountService.haveUser(login));
        verify(databaseService).checkUserRegistered(login);
    }

    @Test
    public void testCountOfUsers() throws Exception {
        when(databaseService.countOfUsers()).thenReturn(42l);
        assertEquals(42l, accountService.getCountOfUsers());
        verify(databaseService).countOfUsers();
    }

    @Test
    public void testAddUserUncorrectLogin() throws Exception {
        String login = "";
        assertEquals(false, accountService.addUser(login, password, email));
        verify(databaseService, never()).addUser(anyString(), anyString(), anyString());
    }

    @Test
    public void testAddUserUncorrectPassword() throws Exception {
        String password = "";
        assertEquals(false, accountService.addUser(login, password, email));
        verify(databaseService, never()).addUser(anyString(), anyString(), anyString());
    }

    @Test
    public void testAddUserUncorrectEmail() throws Exception {
        String email = "";
        assertEquals(false, accountService.addUser(login, password, email));
        verify(databaseService, never()).addUser(anyString(), anyString(), anyString());
    }

    @Test
    public void testAddUserAlreadyRegistered() throws Exception {
        when(databaseService.checkUserRegistered(login)).thenReturn(true);
        assertEquals(false, accountService.addUser(login, password, email));
        verify(databaseService, never()).addUser(anyString(), anyString(), anyString());
    }

    @Test
    public void testAddUserSuccess() throws Exception {
        when(databaseService.checkUserRegistered(login)).thenReturn(false);
        assertEquals(true, accountService.addUser(login, password, email));
        verify(databaseService).addUser(login, password, email);
    }

    @Test
    public void testIsPasswordCorrectTrue() throws Exception {
        UserDataSet user = userDataSets[0];
        when(databaseService.getUser(user.getLogin())).thenReturn(user);
        assertEquals(true, accountService.isPasswordCorrect(user.getLogin(),user.getPassword()));
    }

    @Test
    public void testIsPasswordCorrectFalse() throws Exception {
        UserDataSet user = userDataSets[0];
        when(databaseService.getUser(user.getLogin())).thenReturn(user);
        String uncorrectPass= user.getPassword()+"fsdfs";
        assertEquals(false, accountService.isPasswordCorrect(user.getLogin(),uncorrectPass));
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