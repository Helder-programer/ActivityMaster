package models.workActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
    public void delete(Activity activity) throws Exception {
        super.delete(activity);
    }

}
