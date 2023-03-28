package activities;
import java.time.LocalDate;

public class LeisureActivity extends Activity {

    public LeisureActivity(LocalDate date, int duration, int satisfaction, String description) throws Exception {
        super(date, duration, satisfaction, description);
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
        data += "GASTO DE ENERGIA: " + String.format("%.2f", this.calculateEnergyExpense()) + "\n";
        data += "BEM-ESTAR: " + String.format("%.2f", this.calculateWellBeing()) + "\n";
        return super.toString() + data;
    }

}