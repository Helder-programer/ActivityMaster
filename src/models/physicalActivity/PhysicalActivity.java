package models.physicalActivity;

import java.util.Calendar;
import java.util.List;

import models.activity.Activity;

public class PhysicalActivity extends Activity {
    private int intensivity;
    private static PhysicalActivityDAO physicalActivityDAO = new PhysicalActivityDAO();

    public PhysicalActivity(int id, Calendar date, int duration, int satisfaction, String description, int intensivity,
            int owner) throws Exception {
        super(id, date, duration, satisfaction, description, owner);
        this.setIntensivity(intensivity);
    }

    public PhysicalActivity() {
    }

    public PhysicalActivity(int id, Calendar date, int duration, int satisfaction, String description, int intensivity)
            throws Exception {
        super(id, date, duration, satisfaction, description);
        this.setIntensivity(intensivity);
    }

    public PhysicalActivity(Calendar date, int duration, int satisfaction, String description, int intensivity,
            int owner) throws Exception {
        super(date, duration, satisfaction, description, owner);
        this.setIntensivity(intensivity);
    }

    public void setIntensivity(int intensivity) throws Exception {
        boolean isValidIntensivity = intensivity == 2 || intensivity == 3 || intensivity == 4;
        if (!isValidIntensivity) throw new Exception("A intensidade so pode ter valor 2, 3 ou 4!");
        this.intensivity = intensivity;
    }

    @Override
    public double calculateEnergyExpense() {
        double energyExpense = (this.duration * this.intensivity) * 3;
        return energyExpense;
    }

    @Override
    public double calculateWellBeing() {
        double wellBeing = (this.calculateEnergyExpense() * this.satisfaction) / 360;
        return wellBeing;
    }

    public int getIntensivity() {
        return this.intensivity;
    }

    @Override
    public String toString() {
        String data = "";
        data += "INTENSIDADE: " + this.getIntensivity() + ";\n";
        data += "TIPO DE ATIVIDADE: Física\n";
        data += "GASTO DE ENERGIA: " + this.calculateEnergyExpense() + "\n";
        data += "BEM-ESTAR: " + this.calculateWellBeing() + "\n";
        return super.toString() + data;
    }

    @Override
    public void save() throws Exception {
        physicalActivityDAO.create(this);
    }

    @Override
    public void update() throws Exception {
        physicalActivityDAO.update(this);
    }

    @Override
    public void delete() throws Exception {
        physicalActivityDAO.delete(this);
    }

    public static List<Activity> findAll() throws Exception {
        List<Activity> activities = physicalActivityDAO.findAll();
        return activities;
    }

    public static Activity findById(int id) throws Exception {
        Activity activity = physicalActivityDAO.findById(id);
        return activity;
    }


    public static List<Activity> findByDate(Calendar initialDate, Calendar finalDate) throws Exception {
        List<Activity> activites = physicalActivityDAO.findByDate(initialDate, finalDate);
        return activites;
    }
}