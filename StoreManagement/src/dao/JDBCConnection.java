package src.dao;

import src.utils.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection(){
        String url = Util.URL_JDBC;
        String username = Util.USERNAME_JDBC;
        String password = Util.PASSWORD_JDBC;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
