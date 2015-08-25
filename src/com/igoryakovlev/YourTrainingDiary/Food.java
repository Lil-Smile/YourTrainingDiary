package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Smile on 21.08.15.
 */
public class Food extends Activity implements Constants, View.OnClickListener{


    EditText etFindTheFood;
    Button buttonAddTheFood;
    ListView lvListOfFood;

    ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(10);

    DBHelper dbHelper = new DBHelper(this);

    String keyToFind = "";

    String[] from = {FOOD_NAME,PROTEIN,FAT,CARBOHYDRATES,CALORIFIC};
    int[] to = {R.id.tvNameOfFood,R.id.tvProtein,R.id.tvFat,R.id.tvCarbohydrates,R.id.tvCalorific};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        Log.d("food", "created");

        etFindTheFood = (EditText)findViewById(R.id.etFindTheFood);
        buttonAddTheFood = (Button)findViewById(R.id.buttonAddTheFood);
        lvListOfFood = (ListView)findViewById(R.id.lvListOfFood);

        lvListOfFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getCallingActivity()!=null)
                {
                    HashMap<String,Object> tmp = (HashMap<String,Object>)lvListOfFood.getItemAtPosition(position);
                    String foodName = (String)tmp.get(FOOD_NAME);

                    Log.d("clicked",foodName);
                     //todo: хз как это вытащить
                    Intent data = new Intent();
                    data.putExtra(FOOD_NAME,foodName);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

        buttonAddTheFood.setOnClickListener(this);
       /* etFindTheFood.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                keyToFind = etFindTheFood.getText().toString();
                Log.d("search", keyToFind);
                Cursor cursor;
                if (!keyToFind.equals("")) {
                    cursor = dbHelper.getFoodCursorSearch(keyToFind);
                } else {
                    cursor = dbHelper.getFoodCursor();
                }
                //Log.d("tagged","start find");
                ArrayList<Map<String, Object>> tmpData = new ArrayList<Map<String, Object>>(10);

                if (cursor.moveToFirst()) {
                    int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
                    int proteinColIndex = cursor.getColumnIndex(PROTEIN);
                    int fatColIndex = cursor.getColumnIndex(FAT);
                    int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
                    int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
                    do {
                        Map<String, Object> m = new HashMap<String, Object>(); //setting data to listView
                        m.put(FOOD_NAME, cursor.getString(foodNameColIndex));
                        m.put(PROTEIN, cursor.getDouble(proteinColIndex));
                        m.put(FAT, cursor.getDouble(fatColIndex));
                        m.put(CARBOHYDRATES, cursor.getDouble(carbohydratesColIndex));
                        m.put(CALORIFIC, cursor.getDouble(calorificColIndex));
                        tmpData.add(m);
                    } while (cursor.moveToNext());
                }

                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), tmpData, R.layout.one_food, from, to);
                lvListOfFood.setAdapter(simpleAdapter);

                return true;
            }
        });*/

        etFindTheFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyToFind = etFindTheFood.getText().toString();
                Log.d("search", keyToFind);
                Cursor cursor;
                if (!keyToFind.equals("")) {
                    cursor = dbHelper.getFoodCursorSearch(keyToFind);
                } else {
                    cursor = dbHelper.getFoodCursor();
                }
                //Log.d("tagged","start find");
                ArrayList<Map<String, Object>> tmpData = new ArrayList<Map<String, Object>>(10);

                if (cursor.moveToFirst()) {
                    int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
                    int proteinColIndex = cursor.getColumnIndex(PROTEIN);
                    int fatColIndex = cursor.getColumnIndex(FAT);
                    int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
                    int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
                    do {
                        Map<String, Object> m = new HashMap<String, Object>(); //setting data to listView
                        m.put(FOOD_NAME, cursor.getString(foodNameColIndex));
                        m.put(PROTEIN, cursor.getDouble(proteinColIndex));
                        m.put(FAT, cursor.getDouble(fatColIndex));
                        m.put(CARBOHYDRATES, cursor.getDouble(carbohydratesColIndex));
                        m.put(CALORIFIC, cursor.getDouble(calorificColIndex));
                        tmpData.add(m);
                    } while (cursor.moveToNext());
                }

                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), tmpData, R.layout.one_food, from, to);
                lvListOfFood.setAdapter(simpleAdapter);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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