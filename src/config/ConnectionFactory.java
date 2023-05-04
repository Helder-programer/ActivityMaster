package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db_activty_master", "root",
                    "12345");
            return connection;
        } catch (Exception error) {
            System.out.println("Deu ruim");
        }

        return null;
    }
}
