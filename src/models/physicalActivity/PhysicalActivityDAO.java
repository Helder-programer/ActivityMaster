package models.physicalActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;

import config.ConnectionFactory;
import models.activity.Activity;
import models.activity.ActivityDAO;

public class PhysicalActivityDAO extends ActivityDAO {
    private Connection connection;
    
    public PhysicalActivityDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void create(Activity activity) throws Exception {
        super.create(activity);

        String sql = "INSERT INTO TAB_ATIVIDADES_FISICAS (COD_ATIVIDADE, VAL_INTENSIDADE) VALUES (?,?)";
        PhysicalActivity physicalActivity = new PhysicalActivity();
        physicalActivity = (PhysicalActivity) activity;

        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setInt(1, super.getLastID());
        statement.setInt(2, physicalActivity.getIntensivity());
        statement.execute();
        statement.close();
    }

    @Override
    public void delete(Activity activity)throws Exception {
        super.delete(activity);
    }
}