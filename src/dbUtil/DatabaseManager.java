package dbUtil;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/skyport_management?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD =System.getenv("DB_PASSWORD");

    private  Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
