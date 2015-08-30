package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;


/**
 * Created by Smile on 30.08.15.
 */
public class TrainDiary extends Activity {

    CalendarView calendarView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_diary);

        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

            }
        });
    }
}