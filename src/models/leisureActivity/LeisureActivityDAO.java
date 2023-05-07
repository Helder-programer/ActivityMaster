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
                    A.DES_ATIVIDADE
                FROM
                    TAB_ATIVIDADES as A,
                    TAB_ATIVIDADES_LAZER as L
                WHERE
                    A.COD_ATIVIDADE = L.COD_ATIVIDADE
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

            leisureActivities.add(currentLeisureActivity);
        }
        
        resultSet.close();
        statement.close();
        return leisureActivities;
    }
}