package models.workActivity;

import java.time.LocalDate;

import models.activity.Activity;

public class WorkActivity extends Activity {
    private int dificultity;

    public WorkActivity(LocalDate date, int duration, int satisfaction, String description, int dificultity) throws Exception {
        super(date, duration, satisfaction, description);
        setDificultity(dificultity);
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
   
}