package models.activity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import config.ConnectionFactory;

public class ActivityDAO {
    private Connection connection;
    
    public ActivityDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void create(Activity activity) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(activity.getDateWithoutFormat().getYear(), activity.getDateWithoutFormat().getMonthValue(), activity.getDateWithoutFormat().getDayOfMonth());
        
        PreparedStatement statement = this.connection.prepareStatement("INSERT INTO TAB_ATIVIDADES (COD_ATIVIDADE, DTA_REALIZACAO, NUM_SATISFACAO, DES_ATIVIDADE, COD_USUARIO), VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, activity.getId());
        statement.setDate(2, new java.sql.Date(calendar.getTimeInMillis()));
        statement.setInt(3, activity.getSatisfaction());
        statement.setString(4, activity.getDescription());
        statement.setInt(5, activity.getOwner());
        statement.execute();
        statement.close();
    }

    public int getLastID() throws SQLException {
        int lastID = 0;
        PreparedStatement statement = this.connection.prepareStatement("SELECT COD_ATIVIDADE FROM TAB_ATIVIDADES ORDER BY COD_ATIVIDADE DESC LIMIT 1");
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            lastID = resultSet.getInt(1);
        }
        return lastID;
    }

    public void update(Activity activity) throws SQLException {

    }

    public void delete(Activity activity) throws SQLException {

    }


    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<Activity>();
        return activities;
    }

    public List<Activity> findById() {
        List<Activity> activities = new ArrayList<Activity>();
        return activities;
    }

    public List<Activity> findByDate() {
        List<Activity> activities = new ArrayList<Activity>();
        return activities;
    }

    public void closeConnection() throws SQLException {
        this.connection.close();
    }
}