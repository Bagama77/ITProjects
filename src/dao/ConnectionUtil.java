package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {
    Connection connection;

    public ConnectionUtil(){
        if(connection == null) {
            connection = getConnection();
        }
    }

    private Connection getConnection(){

        String url = "jdbc:mysql://localhost/itprojects";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "010177";

        try {
            System.out.println("Registering driver..");
            Class.forName(driver);
            System.out.println("Settling the connection..");
            connection = DriverManager.getConnection(url, user, password);
        } catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
