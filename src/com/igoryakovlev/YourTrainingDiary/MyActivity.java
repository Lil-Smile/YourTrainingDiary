package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    Button buttonRation;
    Button buttonFood;
    Button buttonGraph;
    Button buttonSettings;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        buttonRation = (Button)findViewById(R.id.buttonRation);
        buttonFood = (Button)findViewById(R.id.buttonFood);
        buttonGraph = (Button)findViewById(R.id.buttonGraph);
        buttonSettings = (Button)findViewById(R.id.buttonSettings);

    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId())
        {
            case R.id.buttonRation:
            {
                intent = new Intent(this,Ration.class);
                break;
            }
            case R.id.buttonFood:
            {
                intent = new Intent(this,Food.class);
                break;
            }
            case R.id.buttonGraph:
            {
                intent = new Intent(this,Graph.class);
                break;
            }
            case R.id.buttonSettings:
            {
                intent = new Intent(this,Settings.class);
                break;
            }
        }
        if (intent!=null)
        {
            startActivity(intent);
        }
    }
}
