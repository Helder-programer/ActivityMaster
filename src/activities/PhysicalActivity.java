package activities;

import java.time.LocalDate;

public class PhysicalActivity extends Activity {
    private int intensivity;

    public PhysicalActivity(LocalDate date, int duration, int satisfaction, String description, int intensivity)
            throws Exception {
        super(date, duration, satisfaction, description);
        setIntensivity(intensivity);
    }

    public void setIntensivity(int intensivity) throws Exception {
        boolean isValidIntensivity = intensivity == 2 || intensivity == 3 || intensivity == 4;
        if (!isValidIntensivity)
            throw new Exception("A intensidade so pode ter valor 2, 3 ou 4!");
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
        data += "GASTO DE ENERGIA: " + String.format("%.2f", this.calculateEnergyExpense()) + "\n";
        data += "BEM-ESTAR: " + String.format("%.2f", this.calculateWellBeing()) + "\n";
        return super.toString() + data;
    }

}