package base;

import dao.UsersDAO;
import dataSets.UserDataSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.*;

/**
 * Created by serg on 21.11.14.
 */
public class DBServiceImpl implements DatabaseService {
    private Connection connection;
    private UsersDAO usersDAO;

    public DBServiceImpl(String db_name) {
        this.connection = getConnection(db_name);
       this.usersDAO = new UsersDAO(connection);
    }


    public static Connection getConnection(String db_name) {
        try{
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").		//db type
                    append("localhost:"). 			//host name
                    append("3306/").				//port
                    append(db_name);			//db name

            Properties properties=new Properties();
            properties.setProperty("user","java_game_user");
            properties.setProperty("password","12345");
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF8");
            //     append("user=forum_user&").			//login
            //     append("password=12345").		//password
            //     append("");
            System.out.append("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString(), properties);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDataSet addUser(String login, String password, String email) {
        UserDataSet user = new UserDataSet(login, password, email);
        try {
            user = usersDAO.put(user);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDataSet getUser(String login) {
        try {
            UserDataSet user = usersDAO.get(login);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long countOfUsers() {
        try {
            long cnt =  usersDAO.count();
            return cnt;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean checkUserRegistered(String login) {
        try {
            boolean res =  usersDAO.contains(login);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(String login) {
        try {
            usersDAO.remove(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
