package activities;

public class LeisureActivity extends Activity{

    public LeisureActivity(int date, int duration, int satisfaction, String description) throws Exception {
        super(date, duration, satisfaction, description);
    }
    
    public int calculateEnergyExpense() {
        return this.duration;
    }

    public double calculateWellBeing() {
        double wellBeing = this.calculateEnergyExpense() * this.satisfaction / 360;
        return wellBeing;
    }

    public String toString() {
        String data = "";
        data += "DATA_REALIZACAO: " + this.getDate() + "; ";
        data += "DURACAO: " + this.getDuration() + "; ";
        data += "DESCRICAO: " + this.getDescription() + "; ";
        data += "TIPO DE ATIVIDADE: Lazer";
        return data;
    }
    
}