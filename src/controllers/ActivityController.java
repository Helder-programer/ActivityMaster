package controllers;

import java.util.ArrayList;
import java.util.List;

import models.activity.Activity;
import models.leisureActivity.LeisureActivityDAO;
import models.physicalActivity.PhysicalActivityDAO;
import models.workActivity.WorkActivityDAO;
import models.physicalActivity.PhysicalActivity;
import models.leisureActivity.LeisureActivity;
import models.workActivity.WorkActivity;

public class ActivityController {
    public Activity findActivityById(int id) throws Exception {
        PhysicalActivityDAO physicalActivityDAO = new PhysicalActivityDAO();
        LeisureActivityDAO leisureActivityDAO = new LeisureActivityDAO();
        WorkActivityDAO workActivityDAO = new WorkActivityDAO();
        List<Activity> activities = new ArrayList<Activity>();

        activities.addAll(physicalActivityDAO.findAll());

        for (Activity activity : activities) {
            if (activity.getId() == id)
                return (PhysicalActivity) activity;
        }

        activities.addAll(leisureActivityDAO.findAll());
        for (Activity activity : activities) {
            if (activity.getId() == id)
                return (LeisureActivity) activity;
        }
        activities.addAll(workActivityDAO.findAll());
        for (Activity activity : activities) {
            if (activity.getId() == id)
                return (WorkActivity) activity;
        }

        return null;
    }

    public List<Activity> findAll() throws Exception {
        List<Activity> activities = new ArrayList<Activity>();
        PhysicalActivityDAO physicalActivityDAO = new PhysicalActivityDAO();
        LeisureActivityDAO leisureActivityDAO = new LeisureActivityDAO();
        WorkActivityDAO workActivityDAO = new WorkActivityDAO();
        

        activities.addAll(physicalActivityDAO.findAll());
        activities.addAll(leisureActivityDAO.findAll());
        activities.addAll(workActivityDAO.findAll());

        return activities;
        
    }
}
