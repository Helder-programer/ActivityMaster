package controllers;

import java.util.Calendar;

import models.activity.Activity;
import models.leisureActivity.LeisureActivity;
import models.leisureActivity.LeisureActivityDAO;

public class LeisureActivityController {
    public void create(Calendar date, int duration, int satisfaction, String description, int owner) throws Exception {
        Activity newActivity = new LeisureActivity(date, duration, satisfaction, description, owner);
        LeisureActivityDAO leisureActivityDAO = new LeisureActivityDAO();
        leisureActivityDAO.create(newActivity);
    }
}
