package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Smile on 30.08.15.
 */
public class Pref extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}