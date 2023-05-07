package controllers;

import java.util.Calendar;

import models.activity.Activity;
import models.physicalActivity.PhysicalActivity;
import models.physicalActivity.PhysicalActivityDAO;

public class PhysicalActivityController {

    public void create(Calendar date, int duration, int satisfaction, String description, int intensivity, int user) throws Exception {

        Activity newActivity = new PhysicalActivity(date, duration, satisfaction, description, intensivity, user);
        PhysicalActivityDAO physicalActivityDAO = new PhysicalActivityDAO();
        physicalActivityDAO.create(newActivity);
    }

    public void update(int id, Calendar date, int duration, int satisfaction, String description, int intensivity, int user) throws Exception {
        PhysicalActivity physicalActivity = new PhysicalActivity(id, date, duration, satisfaction, description, intensivity);
        PhysicalActivityDAO physicalActivityDAO = new PhysicalActivityDAO();
        physicalActivityDAO.update(physicalActivity);
    }

    
}
