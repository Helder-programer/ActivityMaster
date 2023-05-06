package models.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import config.ConnectionFactory;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void register(User user) throws Exception {
        String sql = "INSERT INTO TAB_USUARIOS (NOM_USUARIO, DES_SENHA) VALUES (?,?)";
        PreparedStatement statement = this.connection.prepareStatement(sql);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());

        statement.execute();
        statement.close();
    }

    public boolean login(User user) throws Exception {
        String sql = "SELECT NOM_USUARIO, DES_SENHA FROM TAB_USUARIOS";
        PreparedStatement statement = this.connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String nameTocompare = resultSet.getString(1);
            String passwordToCompare = resultSet.getString(2);

            boolean isAuthenticated = (user.getUsername().equals(nameTocompare) && user.getPassword().equals(passwordToCompare));

            if (isAuthenticated) {
                System.out.println("Autenticado!");
                return true;
            }
        }
        
        System.out.println("Login e/ou senha incorretos!");

        resultSet.close();
        statement.close();
        return false;
    }

}
