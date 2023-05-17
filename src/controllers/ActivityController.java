package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import models.activity.Activity;
import models.leisureActivity.LeisureActivity;
import models.physicalActivity.PhysicalActivity;
import models.workActivity.WorkActivity;
import helpers.ActivityHelpers;

public class ActivityController {
    private ActivityHelpers activityHelpers = new ActivityHelpers();

    public void create(Activity activity) throws Exception {
        activity.save();
    }

    public void update(Activity activity) throws Exception {
        activity.update();
    }

    public void delete(Activity activity) throws Exception {
        activity.delete();
    }

    public Activity findById(int id, int userId) throws Exception {
        Activity searchedActivity;

        searchedActivity = LeisureActivity.findById(id);
        if (searchedActivity != null && isOwner(searchedActivity, userId)) return searchedActivity;

        searchedActivity = PhysicalActivity.findById(id);
        if (searchedActivity != null && isOwner(searchedActivity, userId)) return searchedActivity;

        searchedActivity = WorkActivity.findById(id);
        if (searchedActivity != null && isOwner(searchedActivity, userId)) return searchedActivity;
        
        throw new Exception("Nenhum registro encontrado");
    }

    public List<Activity> findAll(int userId) throws Exception {
        List<Activity> activities = new ArrayList<Activity>();
        List<Activity> authenticatedList = new ArrayList<Activity>();

        activities.addAll(PhysicalActivity.findAll());
        activities.addAll(LeisureActivity.findAll());
        activities.addAll(WorkActivity.findAll());

        for (Activity activity: activities) {
            if (isOwner(activity, userId)) authenticatedList.add(activity);
        }

        activityHelpers.sortActivitiesByDate(authenticatedList);

        return authenticatedList;
    }


    public List<Activity> findByDate(Calendar initialDate, Calendar finalDate, int userId) throws Exception {
        List<Activity> activities = new ArrayList<Activity>();
        List<Activity> authenticatedList = new ArrayList<Activity>();

        activities.addAll(LeisureActivity.findByDate(initialDate, finalDate));
        activities.addAll(PhysicalActivity.findByDate(initialDate, finalDate));
        activities.addAll(WorkActivity.findByDate(initialDate, finalDate));

        for (Activity activity: activities) {
            if (isOwner(activity, userId)) authenticatedList.add(activity);
        }


        activityHelpers.sortActivitiesByDate(authenticatedList);

        return authenticatedList;
    }


    public List<Activity> findByCategory(int activityCategory, int userId) throws Exception {
        List<Activity> activities = new ArrayList<Activity>();
        List<Activity> authenticatedList = new ArrayList<Activity>();


        switch (activityCategory) {
            case 1: // FISICA
                activities.addAll(PhysicalActivity.findAll());
                break;
            case 2: //LAZER
                activities.addAll(LeisureActivity.findAll());
                break;
            case 3: // TRABALHO
                activities.addAll(WorkActivity.findAll());
                break;
            default:
                throw new Exception("Informe uma opcao valida");
        }

        for (Activity activity: activities) {
            if (isOwner(activity, userId)) authenticatedList.add(activity);
        }


        activityHelpers.sortActivitiesByDate(authenticatedList);
        return authenticatedList;
    }


    public List<Activity> ranking(int userId) throws Exception {
        List<Activity> activities = this.findAll(userId);       

        Collections.sort(activities);
        Collections.reverse(activities);


        return activities;
    }

    private static boolean isOwner(Activity activity, int userId) throws Exception {
        if (activity.getOwner() == userId) {
            return true;
        }
        return false;
    }


    
}
