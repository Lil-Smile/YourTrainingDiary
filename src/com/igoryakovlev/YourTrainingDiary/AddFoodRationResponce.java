package com.igoryakovlev.YourTrainingDiary;

import java.util.HashMap;

/**
 * Created by Smile on 25.08.15.
 */
public interface AddFoodRationResponce {

     void onAddingFoodFinished(int weight, int hour, int minute, String eating, HashMap<String,Object> data);
}
