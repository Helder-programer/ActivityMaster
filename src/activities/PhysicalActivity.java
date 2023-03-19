package activities;

public class PhysicalActivity extends Activity{
    private int intensivity;

    public PhysicalActivity(int date, int duration, int satisfaction, String description, int intensivity) throws Exception {
        super(date, duration, satisfaction, description);
        boolean isValidIntensivity = intensivity == 2 || intensivity == 3 || intensivity == 4;
        if (!isValidIntensivity) throw new Exception("A intensidade so pode ter valor 2, 3 ou 4");
        this.intensivity = intensivity;
    }
    
    @Override
    public int calculateEnergyExpense() {
        int energyExpense = (this.duration * this.intensivity) * 3;
        return energyExpense;
    }

    @Override
    public double calculateWellBeing() {
        double wellBeing = this.calculateEnergyExpense() * this.satisfaction / 360;
        return wellBeing;
    }

    public int getIntensivity() {
        return intensivity;
    }

    public void setIntensivity(int intensivity) {
        this.intensivity = intensivity;
    }
    
}