package helpers;

import java.util.List;

import models.activity.Activity;

public class ActivityHelpers {
    public void sortActivitiesByDate(List<Activity> activities) {
        
        for (int counter01 = 0; counter01 < activities.size(); counter01++) {
            for (int counter02 = 0; counter02 < activities.size(); counter02++) {
                boolean isGreatDate = activities.get(counter01).getDateWithoutFormat().getTimeInMillis() < activities.get(counter02).getDateWithoutFormat().getTimeInMillis();
                if (isGreatDate) {

                    Activity assistentActivity = activities.get(counter01);
                    
                    activities.set(counter01, activities.get(counter02));
                    activities.set(counter02, assistentActivity);
                

                }
            }
        }

    }
}
