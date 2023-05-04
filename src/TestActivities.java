import models.activity.Activity;
import models.activity.ActivityDAO;
import models.leisureActivity.LeisureActivity;
import models.leisureActivity.LeisureActivityDAO;
import models.physicalActivity.PhysicalActivity;
import models.physicalActivity.PhysicalActivityDAO;
import models.workActivity.WorkActivity;
import models.workActivity.WorkActivityDAO;

// import java.util.List;
// import java.util.Scanner;
// import java.util.ArrayList;
import java.util.Calendar;
// import java.util.Collections;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestActivities {
    public void add() {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate activityDate = LocalDate.parse("01/01/2023", date);
        Calendar calendar = Calendar.getInstance();
        
        
    }

}