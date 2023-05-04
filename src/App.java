import java.sql.Connection;

import config.ConnectionFactory;

public class App {
    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
        System.out.println("Conectado com o banco!");
    }
}
