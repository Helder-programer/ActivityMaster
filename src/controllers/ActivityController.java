package controllers;

import java.util.ArrayList;
import java.util.List;

import models.activity.Activity;
import models.leisureActivity.LeisureActivity;
import models.physicalActivity.PhysicalActivity;
import models.workActivity.WorkActivity;

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


    public Activity findActivityById(int id) throws Exception {
        Activity searchedActivity;

        searchedActivity = LeisureActivity.findById(id);
        if (searchedActivity != null) return searchedActivity;

        searchedActivity = PhysicalActivity.findById(id);
        if (searchedActivity != null) return searchedActivity;

        searchedActivity = WorkActivity.findById(id);
        
        throw new Exception("Nenhum registro encontrado");
    }

    public List<Activity> findAll() throws Exception {
        List<Activity> activities = new ArrayList<Activity>();

        activities.addAll(PhysicalActivity.findAll());
        activities.addAll(LeisureActivity.findAll());
        activities.addAll(WorkActivity.findAll());

        return activities;
                
    }
}
