package controllers;

import java.util.Calendar;

import models.activity.Activity;
import models.workActivity.WorkActivity;
import models.workActivity.WorkActivityDAO;

public class WorkActivityController {
    public void create(Calendar date, int duration, int satisfaction, String description, int dificultity, int owner) throws Exception {
        Activity newActivity = new WorkActivity(date, duration, satisfaction, description, dificultity, owner);
        WorkActivityDAO workActivityDAO = new WorkActivityDAO();
        workActivityDAO.create(newActivity);
    }

}
