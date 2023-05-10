package models.workActivity;
import java.util.Calendar;
import java.util.List;

import models.activity.Activity;

public class WorkActivity extends Activity {
    private int dificultity;

    private static WorkActivityDAO workActivityDAO = new WorkActivityDAO();

    public WorkActivity(int id, Calendar date, int duration, int satisfaction, String description, int dificultity, int owner) throws Exception {
        super(id, date, duration, satisfaction, description, owner);
        this.setDificultity(dificultity);
    }
    
    public WorkActivity() {}

    public WorkActivity(int id, Calendar date, int duration, int satisfaction, String description, int dificultity) throws Exception {
        super(id, date, duration, satisfaction, description);
        this.setDificultity(dificultity);
    }

    public WorkActivity(Calendar date, int duration, int satisfaction, String description, int dificultity, int owner) throws Exception {
        super(date, duration, satisfaction, description, owner);
        this.setDificultity(dificultity);
    }
    
    public void setDificultity(int dificultity) throws Exception {
        boolean isValidDificultity = dificultity == 1 || dificultity == 2 || dificultity == 3;
        if (!isValidDificultity) throw new Exception("A dificuldade so pode ter valor 1, 2 ou 3!");
        this.dificultity = dificultity;
    }


    @Override
    public double calculateEnergyExpense() {
        double energyExpense = (this.duration * this.dificultity) * 2;
        return energyExpense;
    }

    @Override
    public double calculateWellBeing() {
        double wellBeing = this.calculateEnergyExpense() * this.satisfaction / 360;
        return wellBeing;
    }


    public int getDificultity() {
        return this.dificultity;
    }

    @Override
    public String toString() {
        String data = "";
        data += "DIFICULDADE: " + this.getDificultity() + ";\n";
        data += "TIPO DE ATIVIDADE: Trabalho\n";
        data += "GASTO DE ENERGIA: " + String.format("%.2f", this.calculateEnergyExpense()) + "\n";
        data += "BEM-ESTAR: " + this.calculateWellBeing() + "\n";
        return super.toString() + data;
    }

    @Override
    public void save() throws Exception {
        workActivityDAO.create(this);       
        
    }

    @Override
    public void update() throws Exception {
        workActivityDAO.update(this);
        
    }

    @Override
    public void delete() throws Exception {
        workActivityDAO.delete(this);
    }
   
    public static List<Activity> findAll() throws Exception {
        List<Activity> activities = workActivityDAO.findAll();
        return activities;
    }

    public static Activity findById(int id) throws Exception {
        Activity activity = workActivityDAO.findById(id);
        return activity;
    }
}