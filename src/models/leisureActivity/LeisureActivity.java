package models.leisureActivity;
import java.util.Calendar;
import java.util.List;

import models.activity.Activity;

public class LeisureActivity extends Activity {
    private static LeisureActivityDAO leisureActivityDAO = new LeisureActivityDAO();
    
    public LeisureActivity(int id, Calendar date, int duration, int satisfaction, String description, int owner) throws Exception {
        super(id, date, duration, satisfaction, description, owner);
    }
    
    public LeisureActivity() {}


    public LeisureActivity(int id, Calendar date, int duration, int satisfaction, String description) throws Exception {
        super(id, date, duration, satisfaction, description);
    }

    public LeisureActivity(Calendar date, int duration, int satisfaction, String description, int owner) throws Exception {
        super(date, duration, satisfaction, description, owner);
    }



    @Override
    public double calculateEnergyExpense() {
        return this.duration;
    }
    
    @Override
    public double calculateWellBeing() {
        double wellBeing = this.calculateEnergyExpense() * this.satisfaction / 360;
        return wellBeing;
    }

    @Override
    public String toString() {
        String data = "";
        data += "TIPO DE ATIVIDADE: Lazer\n";
        data += "GASTO DE ENERGIA: " + this.calculateEnergyExpense() + "\n";
        data += "BEM-ESTAR: " + this.calculateWellBeing() + "\n";
        return super.toString() + data;
    }

    @Override
    public void save() throws Exception {
        leisureActivityDAO.create(this);
    }

    @Override
    public void update() throws Exception {
        leisureActivityDAO.update(this);
    }
    
    @Override
    public void delete() throws Exception {
        leisureActivityDAO.delete(this);
    }


    
    public static List<Activity> findAll() throws Exception {
        List<Activity> activites = leisureActivityDAO.findAll();
        return activites;
    }


    public static Activity findById(int id) throws Exception {
        Activity activity = leisureActivityDAO.findById(id);
        return activity;
    }

}