package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Smile on 21.08.15.
 */
public class Food extends Activity implements Constants, View.OnClickListener{

    EditText etFindTheFood;
    Button buttonAddTheFood;
    ListView lvListOfFood;

    ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(10);

    DBHelper dbHelper = new DBHelper(this);



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        Log.d("food", "created");

        etFindTheFood = (EditText)findViewById(R.id.etFindTheFood);
        buttonAddTheFood = (Button)findViewById(R.id.buttonAddTheFood);
        lvListOfFood = (ListView)findViewById(R.id.lvListOfFood);

        buttonAddTheFood.setOnClickListener(this);

        Cursor cursor = dbHelper.getFoodCursor(); //getting data from db
        if (cursor.moveToFirst())
        {
            int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
            int proteinColIndex = cursor.getColumnIndex(PROTEIN);
            int fatColIndex = cursor.getColumnIndex(FAT);
            int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
            int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
            do {
                Map<String,Object> m = new HashMap<String, Object>(); //setting data to listView
                m.put(FOOD_NAME,cursor.getString(foodNameColIndex));
                m.put(PROTEIN,cursor.getDouble(proteinColIndex));
                m.put(FAT,cursor.getDouble(fatColIndex));
                m.put(CARBOHYDRATES, cursor.getDouble(carbohydratesColIndex));
                m.put(CALORIFIC,cursor.getDouble(calorificColIndex));
                data.add(m);
            } while (cursor.moveToNext());
        }


        String[] from = {FOOD_NAME,PROTEIN,FAT,CARBOHYDRATES,CALORIFIC};
        int[] to = {R.id.tvNameOfFood,R.id.tvProtein,R.id.tvFat,R.id.tvCarbohydrates,R.id.tvCalorific};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.one_food,from,to); //hope this works

        lvListOfFood.setAdapter(simpleAdapter);

        Log.d("food","added");



        //SimpleAdapter simpleAdapter = new SimpleAdapter(this,)

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonAddTheFood)
        {
            DialogFragment dialogFragment = new FoodDialogFragment();
            dialogFragment.show(getFragmentManager(),"dialog");
        }
    }
}