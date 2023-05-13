package models.leisureActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import config.ConnectionFactory;
import models.activity.Activity;
import models.activity.ActivityDAO;

public class LeisureActivityDAO extends ActivityDAO {
    private Connection connection;

    public LeisureActivityDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void create(Activity activity) throws Exception {
        super.create(activity);
        PreparedStatement statement = this.connection.prepareStatement("INSERT INTO TAB_ATIVIDADES_LAZER VALUES (?)");
        statement.setInt(1, super.getLastID());
        statement.execute();
        statement.close();
    }

    @Override
    public void update(Activity activity) throws Exception {
        super.update(activity);
    }

    @Override
    public void delete(Activity activity) throws Exception {
        super.delete(activity);
    }

    @Override
    public List<Activity> findAll() throws Exception {

        List<Activity> leisureActivities = new ArrayList<Activity>();

        String sql = """
                SELECT
                    A.COD_ATIVIDADE,
                    A.DTA_REALIZACAO,
                    A.VAL_DURACAO,
                    A.VAL_SATISFACAO,
                    A.DES_ATIVIDADE,
                    A.COD_USUARIO
                FROM
                    TAB_ATIVIDADES as A,
                    TAB_ATIVIDADES_LAZER as L
                WHERE
                    A.COD_ATIVIDADE = L.COD_ATIVIDADE
                    ORDER BY A.DTA_REALIZACAO
                """;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            LeisureActivity currentLeisureActivity = new LeisureActivity();
            Calendar calendar = Calendar.getInstance();

            currentLeisureActivity.setId(resultSet.getInt(1));
            calendar.setTime(resultSet.getDate(2));
            currentLeisureActivity.setDate(calendar);
            currentLeisureActivity.setDuration(resultSet.getInt(3));
            currentLeisureActivity.setSatisfaction(resultSet.getInt(4));
            currentLeisureActivity.setDescription(resultSet.getString(5));
            currentLeisureActivity.setOwner(resultSet.getInt(6));

            leisureActivities.add(currentLeisureActivity);
        }

        resultSet.close();
        statement.close();
        return leisureActivities;
    }

    @Override
    public Activity findById(int id) throws Exception {
        LeisureActivity searchedLeisureActivity;
        String sql = """
                SELECT
                    A.COD_ATIVIDADE,
                    A.DTA_REALIZACAO,
                    A.VAL_DURACAO,
                    A.VAL_SATISFACAO,
                    A.DES_ATIVIDADE,
                    A.COD_USUARIO
                FROM
                    TAB_ATIVIDADES AS A,
                    TAB_ATIVIDADES_LAZER AS L
                WHERE
                    A.COD_ATIVIDADE = L.COD_ATIVIDADE
                    AND A.COD_ATIVIDADE = ?
                """;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            searchedLeisureActivity = new LeisureActivity();
            Calendar calendar = Calendar.getInstance();
            searchedLeisureActivity.setId(resultSet.getInt(1));
            calendar.setTime(resultSet.getDate(2));
            searchedLeisureActivity.setDate(calendar);
            searchedLeisureActivity.setDuration(resultSet.getInt(3));
            searchedLeisureActivity.setSatisfaction(resultSet.getInt(4));
            searchedLeisureActivity.setDescription(resultSet.getString(5));
            searchedLeisureActivity.setOwner((resultSet.getInt(6)));
            return searchedLeisureActivity;
        }
        return null;
    }

    @Override
    public List<Activity> findByDate(Calendar initialDate, Calendar finalDate) throws Exception {
        List<Activity> activities = new ArrayList<Activity>();

        String sql = """
                    SELECT
                    A.COD_ATIVIDADE,
                    A.DTA_REALIZACAO,
                    A.VAL_DURACAO,
                    A.VAL_SATISFACAO,
                    A.DES_ATIVIDADE,
                    A.COD_USUARIO
                FROM
                    TAB_ATIVIDADES AS A,
                    TAB_ATIVIDADES_LAZER AS L
                WHERE
                    A.COD_ATIVIDADE = L.COD_ATIVIDADE
                    AND A.DTA_REALIZACAO BETWEEN ? AND ?
                        """;

        PreparedStatement statement = this.connection.prepareStatement(sql);

        statement.setDate(1, new java.sql.Date(initialDate.getTimeInMillis()));
        statement.setDate(2, new java.sql.Date(finalDate.getTimeInMillis()));

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            LeisureActivity currentLeisureActivity = new LeisureActivity();

            Calendar calendar = Calendar.getInstance();
            currentLeisureActivity.setId(resultSet.getInt(1));
            calendar.setTime(resultSet.getDate(2));
            currentLeisureActivity.setDate(calendar);
            currentLeisureActivity.setDuration(resultSet.getInt(3));
            currentLeisureActivity.setSatisfaction(resultSet.getInt(4));
            currentLeisureActivity.setDescription(resultSet.getString(5));
            currentLeisureActivity.setOwner(resultSet.getInt(6));

            
            activities.add(currentLeisureActivity);

        }

        return activities;
    }
}