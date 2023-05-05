import models.activity.Activity;
import models.activity.ActivityDAO;
import models.leisureActivity.LeisureActivity;
import models.leisureActivity.LeisureActivityDAO;
import models.physicalActivity.PhysicalActivity;
import models.physicalActivity.PhysicalActivityDAO;
import models.workActivity.WorkActivity;
import models.workActivity.WorkActivityDAO;

// import models.physicalActivity.PhysicalActivity;
// import models.physicalActivity.PhysicalActivityDAO;
// import models.workActivity.WorkActivity;
// import models.workActivity.WorkActivityDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TestActivities {
    public void add() {

        try {
            DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate activityDate = LocalDate.parse("01/01/2023", date);
            Calendar dateCalendar  = Calendar.getInstance();
            dateCalendar.set(activityDate.getYear(), activityDate.getMonthValue() - 1, activityDate.getDayOfMonth());

            WorkActivity physicalActivity = new WorkActivity();
            physicalActivity.setDate(dateCalendar);
            physicalActivity.setDuration(10);
            physicalActivity.setSatisfaction(1);
            physicalActivity.setDescription("Next");
            physicalActivity.setOwner(1);
            physicalActivity.setDificultity(2);

            PhysicalActivityDAO activityDAO = new PhysicalActivityDAO();
            activityDAO.create(physicalActivity);
            System.out.println("Gravado!");
        } catch (Exception error) {
            System.out.println(error);
        }
    }

}