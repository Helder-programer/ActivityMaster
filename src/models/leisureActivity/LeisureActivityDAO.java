package models.leisureActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.ConnectionFactory;
import models.activity.Activity;
import models.activity.ActivityDAO;

public class LeisureActivityDAO extends ActivityDAO {
    private Connection connection;
    
    public LeisureActivityDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void create(Activity activity) throws SQLException {
        super.create(activity);
        PreparedStatement statement = this.connection.prepareStatement("INSERT INTO TAB_ATIVIDADES_LAZER VALUES (?)");
        statement.setInt(1, activity.getId());
        statement.execute();
        statement.close();
    }

    @Override
    public void update(Activity activity) throws SQLException {
        super.update(activity);
    }
}
