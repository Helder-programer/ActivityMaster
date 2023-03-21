package activities;

public abstract class Activity {
    protected int id;
    protected int date;
    protected int duration;
    protected int satisfaction;
    protected String description;

    public Activity(int date, int duration, int satisfaction, String description) throws Exception {
        boolean isValidSatisfaction = satisfaction == 1 || satisfaction == -1;
        boolean isValidDuration = duration > 0;
        if (!isValidSatisfaction) throw new Exception("A satisfacao so pode ter valor de 1 ou -1");
        if (!isValidDuration) throw new Exception("A duracao deve ser maior do que 0");
        
        this.date = date;
        this.duration = duration;
        this.satisfaction = satisfaction;
        this.description = description;
    }


    public abstract int calculateEnergyExpense();

    public abstract double calculateWellBeing();

    public String toString() {
        return "";
    };

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}