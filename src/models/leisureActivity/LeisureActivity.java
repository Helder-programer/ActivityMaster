package models.leisureActivity;

import java.util.Calendar;

import models.activity.Activity;

public class LeisureActivity extends Activity {


    
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

}