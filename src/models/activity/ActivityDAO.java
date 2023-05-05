package models.activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import config.ConnectionFactory;
import models.workActivity.WorkActivity;

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

    public List<Activity> findAll(String[] whereConditions) throws Exception {
        List<Activity> activities = new ArrayList<Activity>();

        String[] array = { "nada" };
        activities.addAll(this.findWorkActivities(array));

        return activities;
    }

    public List<Activity> findWorkActivities(String[] whereConditions) throws Exception {
        List<Activity> workActivities = new ArrayList<Activity>();

        String sql = """
                SELECT
                    A.COD_ATIVIDADE,
                    A.DTA_REALIZACAO,
                    A.VAL_DURACAO,
                    A.VAL_SATISFACAO,
                    A.DES_ATIVIDADE,
                    T.VAL_DIFICULDADE
                FROM
                    TAB_ATIVIDADES as A,
                    TAB_ATIVIDADES_TRABALHO as T
                WHERE
                    A.COD_ATIVIDADE = T.COD_ATIVIDADE
                """;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            WorkActivity currentWorkActivity = new WorkActivity();
            Calendar calendar = Calendar.getInstance();

            currentWorkActivity.setId(resultSet.getInt(1));
            currentWorkActivity.setDate(calendar);
            calendar.setTime(resultSet.getDate(2));
            currentWorkActivity.setDuration(resultSet.getInt(3));
            currentWorkActivity.setSatisfaction(resultSet.getInt(4));
            currentWorkActivity.setDescription((resultSet.getString(5)));
            currentWorkActivity.setOwner((resultSet.getInt(6)));

        }

        statement.close();
        return workActivities;
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
}