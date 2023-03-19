import activities.Activity;
import activities.LeisureActivity;
import activities.PhysicalActivity;
import activities.WorkActivity;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Activity newActivity = new WorkActivity(10, 1, 1, "Estudar", 0);
            System.out.println(newActivity.getDescription());
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
