package activities;

public class WorkActivity  extends Activity{
    private int dificultity;

    public WorkActivity(int date, int duration, int satisfaction, String description, int dificultity) throws Exception {
        super(date, duration, satisfaction, description);
        boolean isValidDificultity = dificultity == 1 || dificultity == 2 || dificultity == 3;
        if (!isValidDificultity) throw new Exception("A dificuldade so pode ter valor 1, 2 ou 3");
        this.dificultity = dificultity;
    }
 
    
    public int calculateEnergyExpense() {
        int energyExpense = (this.duration * this.dificultity) * 2;
        return energyExpense;
    }

    public double calculateWellBeing() {
        double wellBeing = this.calculateEnergyExpense() * this.satisfaction / 360;
        return wellBeing;
    }


    public int getDificultity() {
        return dificultity;
    }


    public void setDificultity(int dificultity) {
        this.dificultity = dificultity;
    }


    
}