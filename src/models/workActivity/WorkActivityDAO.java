package models.workActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import config.ConnectionFactory;
import models.activity.Activity;
import models.activity.ActivityDAO;

public class WorkActivityDAO extends ActivityDAO {
    private Connection connection;

    public WorkActivityDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void create(Activity activity) throws Exception {
        super.create(activity);
        String sql = "INSERT INTO TAB_ATIVIDADES_TRABALHO (COD_ATIVIDADE, VAL_DIFICULDADE) VALUES (?,?)";
        WorkActivity workActivity = new WorkActivity();
        workActivity = (WorkActivity) activity;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, super.getLastID());
        statement.setInt(2, workActivity.getDificultity());
        statement.execute();
        statement.close();
    }

    @Override
    public void update(Activity activity) throws Exception {
        super.update(activity);
        String sql = "UPDATE TAB_ATIVIDADES_TRABALHO SET VAL_DIFICULDADE = ? WHERE COD_ATIVIDADE = ?";
        WorkActivity workActivity = new WorkActivity();
        workActivity = (WorkActivity) activity;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, workActivity.getDificultity());
        statement.setInt(2, workActivity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Activity activity) throws Exception {
        super.delete(activity);
    }

    @Override
    public List<Activity> findAll() throws Exception {

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
                ORDER BY
                    A.DTA_REALIZACAO, A.COD_ATIVIDADE DESC
                """;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            WorkActivity currentWorkActivity = new WorkActivity();
            Calendar calendar = Calendar.getInstance();

            currentWorkActivity.setId(resultSet.getInt(1));
            calendar.setTime(resultSet.getDate(2));
            currentWorkActivity.setDate(calendar);
            currentWorkActivity.setDuration(resultSet.getInt(3));
            currentWorkActivity.setSatisfaction(resultSet.getInt(4));
            currentWorkActivity.setDescription(resultSet.getString(5));
            currentWorkActivity.setDificultity(resultSet.getInt(6));

            workActivities.add(currentWorkActivity);
        }
        
        resultSet.close();
        statement.close();
        return workActivities;
    }
}
