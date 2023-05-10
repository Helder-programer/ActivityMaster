package controllers;

import java.util.ArrayList;
import java.util.List;

import models.activity.Activity;

public class ActivityController {
    public void create(Activity activity) throws Exception {
        activity.save();
    }

    public void update(Activity activity) throws Exception {
        activity.update();
    }

    public void delete(Activity activity) throws Exception {
        activity.delete();
    }
    
    public List<Activity> showAllActivities() {
        List<Activity> activities = new ArrayList<Activity>();
        
        return activities;
    }
}
