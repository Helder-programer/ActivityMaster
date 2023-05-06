package models.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.ConnectionFactory;

public abstract class ActivityDAO {
    private Connection connection;

    protected ActivityDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void create(Activity activity) throws Exception {
        String sql = """
                INSERT INTO TAB_ATIVIDADES
                (
                    COD_ATIVIDADE,
                    DTA_REALIZACAO,
                    VAL_DURACAO,
                    VAL_SATISFACAO,
                    DES_ATIVIDADE,
                    COD_USUARIO
                )
                VALUES (?,?,?,?,?,?)
                    """;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, this.getLastID() + 1);
        statement.setDate(2, new java.sql.Date(activity.getDateWithoutFormat().getTimeInMillis()));
        statement.setInt(3, activity.getDuration());
        statement.setInt(4, activity.getSatisfaction());
        statement.setString(5, activity.getDescription());
        statement.setInt(6, 1);
        
        statement.execute();
        statement.close();
    }

    public int getLastID() throws Exception {
        String sql = "SELECT COD_ATIVIDADE FROM TAB_ATIVIDADES ORDER BY COD_ATIVIDADE DESC LIMIT 1";
        int lastID = 0;
        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            lastID = resultSet.getInt(1);
        }
        return lastID;
    }

    public void update(Activity activity) throws Exception {
        
    }

    public void delete(Activity activity) throws Exception {
        String sql = "DELETE FROM TAB_ATIVIDADES WHERE COD_ATIVIDADE = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, activity.getId());
        statement.execute();
        statement.close();
    }


    public List<Activity> findById() {
        List<Activity> activities = new ArrayList<Activity>();
        return activities;
    }
    
    public List<Activity> findByDate() {
        List<Activity> activities = new ArrayList<Activity>();
        return activities;
    }
    
    public void closeConnection() throws Exception {
        this.connection.close();
    }
    
    public abstract List<Activity> findAll() throws Exception;
}