package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Smile on 21.08.15.
 */
public class Food extends Activity {

    EditText etFindTheFood;
    Button buttonAddTheFood;
    ListView lvListOfFood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        etFindTheFood = (EditText)findViewById(R.id.etFindTheFood);
        buttonAddTheFood = (Button)findViewById(R.id.buttonAddTheFood);
        lvListOfFood = (ListView)findViewById(R.id.lvListOfFood);



    }
}