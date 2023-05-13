import models.activity.Activity;
import models.physicalActivity.PhysicalActivity;
import models.physicalActivity.PhysicalActivityDAO;

public class App {
    public static void main(String[] args) {
        ActivityManager activityManager = new ActivityManager();
        activityManager.init();
        
        Activity physicalActivity = new PhysicalActivity();
        PhysicalActivityDAO physicalActivityDAO = new PhysicalActivityDAO();


        try {
            physicalActivityDAO.create(physicalActivity);




            
        }catch(Exception error) {
            System.out.println(error);
        }




    }
}
