package models.activity;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Activity implements Comparable<Activity> {
    protected int id;
    protected Calendar date;
    protected int duration;
    protected int satisfaction;
    protected String description;
    protected int owner;

    public abstract double calculateEnergyExpense();

    public abstract double calculateWellBeing();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(this.date.getTime());
        return date;
    }

    public Calendar getDateWithoutFormat() {
        return this.date;
    }

    public int getDuration() {
        return duration;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDuration(int duration) throws Exception {
        boolean isValidDuration = duration > 0;
        if (!isValidDuration) throw new Exception("A duracao deve ser maior do que 0!");
        this.duration = duration;
    }

    public void setSatisfaction(int satisfaction) throws Exception{
        boolean isValidSatisfaction = satisfaction == 1 || satisfaction == -1;
        if (!isValidSatisfaction) throw new Exception("A satisfacao so pode ter valor de 1 ou -1!");
        this.satisfaction = satisfaction;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        String data = "";
        data += "ID: " + this.getId() + ";\n";
        data += "DATA_REALIZACAO: " + this.getDate() + ";\n";
        data += "DURACAO: " + this.getDuration() + ";\n";
        data += "SATISFACAO: " + this.getSatisfaction() + ";\n";
        data += "DESCRICAO: " + this.getDescription() + "; \n";
        return data;
    }

    @Override
    public int compareTo(Activity activity) {
        double firstValue = this.calculateEnergyExpense();
        double secondValue = (activity).calculateEnergyExpense();

        if (firstValue > secondValue) return 1;
        else if (firstValue < secondValue) return -1;
        return 0;
    }

}