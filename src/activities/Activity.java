package activities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Activity implements Comparable {
    protected LocalDate date;
    protected int duration;
    protected int satisfaction;
    protected String description;

    public Activity(LocalDate date, int duration, int satisfaction, String description) throws Exception {
        setDate(date);
        setDuration(duration);
        setSatisfaction(satisfaction);
        setDescription(description);
    }

    public abstract double calculateEnergyExpense();

    public abstract double calculateWellBeing();

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = this.date.format(formatter);
        return date;
    }

    public LocalDate getDateWithoutFormat() {
        return this.date;
    }

    public int getDuration() {
        return duration;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setDate(LocalDate date) {
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

    public String toString() {
        String data = "";
        data += "DATA_REALIZACAO: " + this.getDate() + ";\n";
        data += "DURACAO: " + this.getDuration() + ";\n";
        data += "SATISFACAO: " + this.getSatisfaction() + ";\n";
        data += "DESCRICAO: " + this.getDescription() + "; \n";
        return data;
    }

    @Override
    public int compareTo(Object activity) {
        return Integer.parseInt("" + this.calculateEnergyExpense()) - Integer.parseInt("" + ((Activity) activity).calculateEnergyExpense());
    }

}