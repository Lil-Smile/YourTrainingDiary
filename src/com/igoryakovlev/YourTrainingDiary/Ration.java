package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.*;

/**
 * Created by Smile on 21.08.15.
 */
public class Ration extends Activity implements View.OnClickListener, Constants, AddFoodRationResponce {

    static int REQUEST_CODE = 17;

    static int currentYear, currentMonth, currentDay;

    TextView tvProt, tvFat, tvCarbo, tvCalor;
    ListView listView;
    Button buttonAdd;

    DBHelper dbHelper;

    ArrayList<Map<String,Object>> data = new ArrayList<Map<String, Object>>(10);

    String[] from = {FOOD_NAME,PROTEIN,FAT,CARBOHYDRATES,CALORIFIC,EATING,TIME,DATA};
    int[] to = {R.id.tvFoodNameRation,R.id.tvFoodProtRation,R.id.tvFoodFatRation,R.id.tvFoodCarboRation,R.id.tvFoodCalorRation,R.id.tvEatingRation,R.id.tvTimeRation,R.id.tvDateRation};

    float currentProt, currentFat, currentCarbo, currentCalor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ration);

        currentProt=0f;
        currentFat=0f;
        currentCarbo=0f;
        currentCalor=0f;

        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        tvProt = (TextView)findViewById(R.id.tvProtRation);
        tvFat = (TextView)findViewById(R.id.tvFatRation);
        tvCarbo = (TextView)findViewById(R.id.tvCarboRation);
        tvCalor = (TextView)findViewById(R.id.tvCalorRation);

        listView = (ListView)findViewById(R.id.lvRation);

        listView.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDecector = new GestureDetector(getApplicationContext(), new SwipeGestureDetectore());

            public void onSwipeLeft()
            {
                currentProt=currentCalor=currentCarbo=currentFat=0;
                currentDay+=1;
                String dateKey = currentYear+"."+currentMonth+"."+currentDay;//формат даты!
                Cursor cursor = dbHelper.getRationCursor(dateKey);
                ArrayList<Map<String,Object>> tmpData = new ArrayList<Map<String, Object>>();
                if (cursor.moveToFirst())
                {

                    int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
                    int proteinColIndex = cursor.getColumnIndex(PROTEIN);
                    int fatColIndex = cursor.getColumnIndex(FAT);
                    int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
                    int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
                    int eatingColIndex = cursor.getColumnIndex(EATING);
                    int timeColIndex = cursor.getColumnIndex(TIME);
                    int dateColIndex = cursor.getColumnIndex(DATA);

                    do {
                        Map<String,Object> m = new HashMap<String, Object>();
                        m.put(FOOD_NAME,cursor.getString(foodNameColIndex));
                        m.put(PROTEIN,cursor.getFloat(proteinColIndex));
                        m.put(FAT,cursor.getFloat(fatColIndex));
                        m.put(CARBOHYDRATES,cursor.getFloat(carbohydratesColIndex));
                        m.put(CALORIFIC,cursor.getFloat(calorificColIndex));
                        m.put(EATING,cursor.getString(eatingColIndex));
                        m.put(TIME,cursor.getString(timeColIndex));
                        m.put(DATA, cursor.getString(dateColIndex));
                        tmpData.add(m);
                        Log.d("food", (String) m.get(FOOD_NAME) + " " + (Float) m.get(PROTEIN) + " " + (Float) m.get(FAT) + " " + (Float) m.get(CARBOHYDRATES) + " " + (Float) m.get(CALORIFIC) + " " + (String) m.get(EATING) + " " + (String) m.get(TIME) + " " + (String) m.get(DATA));
                        currentProt+=cursor.getFloat(proteinColIndex);
                        currentFat+=cursor.getFloat(fatColIndex);
                        currentCarbo+=cursor.getFloat(carbohydratesColIndex);
                        currentCalor+=cursor.getFloat(calorificColIndex);
                    } while(cursor.moveToNext());
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),tmpData,R.layout.one_ration,from,to);
                listView.setAdapter(simpleAdapter);
                tvProt.setText(getResources().getString(R.string.protein)+currentProt);
                tvFat.setText(getResources().getString(R.string.fat)+currentFat);
                tvCarbo.setText(getResources().getString(R.string.carbohydrates) + currentCarbo);
                tvCalor.setText(getResources().getString(R.string.calorifical) + currentCalor);

            }

            public void onSwipeRight()
            {
                currentProt=currentCalor=currentCarbo=currentFat=0;
                currentDay-=1;
                String dateKey = currentYear+"."+currentMonth+"."+currentDay;//формат даты!
                Cursor cursor = dbHelper.getRationCursor(dateKey);
                ArrayList<Map<String,Object>> tmpData = new ArrayList<Map<String, Object>>();
                if (cursor.moveToFirst())
                {

                    int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
                    int proteinColIndex = cursor.getColumnIndex(PROTEIN);
                    int fatColIndex = cursor.getColumnIndex(FAT);
                    int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
                    int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
                    int eatingColIndex = cursor.getColumnIndex(EATING);
                    int timeColIndex = cursor.getColumnIndex(TIME);
                    int dateColIndex = cursor.getColumnIndex(DATA);

                    do {
                        Map<String,Object> m = new HashMap<String, Object>();
                        m.put(FOOD_NAME,cursor.getString(foodNameColIndex));
                        m.put(PROTEIN,cursor.getFloat(proteinColIndex));
                        m.put(FAT,cursor.getFloat(fatColIndex));
                        m.put(CARBOHYDRATES,cursor.getFloat(carbohydratesColIndex));
                        m.put(CALORIFIC,cursor.getFloat(calorificColIndex));
                        m.put(EATING,cursor.getString(eatingColIndex));
                        m.put(TIME,cursor.getString(timeColIndex));
                        m.put(DATA, cursor.getString(dateColIndex));
                        tmpData.add(m);
                        Log.d("food", (String) m.get(FOOD_NAME) + " " + (Float) m.get(PROTEIN) + " " + (Float) m.get(FAT) + " " + (Float) m.get(CARBOHYDRATES) + " " + (Float) m.get(CALORIFIC) + " " + (String) m.get(EATING) + " " + (String) m.get(TIME) + " " + (String) m.get(DATA));
                        currentProt+=cursor.getFloat(proteinColIndex);
                        currentFat+=cursor.getFloat(fatColIndex);
                        currentCarbo+=cursor.getFloat(carbohydratesColIndex);
                        currentCalor+=cursor.getFloat(calorificColIndex);
                    } while(cursor.moveToNext());
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),tmpData,R.layout.one_ration,from,to);
                listView.setAdapter(simpleAdapter);
                tvProt.setText(getResources().getString(R.string.protein)+currentProt);
                tvFat.setText(getResources().getString(R.string.fat)+currentFat);
                tvCarbo.setText(getResources().getString(R.string.carbohydrates) + currentCarbo);
                tvCalor.setText(getResources().getString(R.string.calorifical) + currentCalor);
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDecector.onTouchEvent(event);
            }

            class SwipeGestureDetectore extends GestureDetector.SimpleOnGestureListener
            {
                private static final int SWIPE_DISTANCE_THRESHOLD = 100;
                private static final int SWIPE_VELOCITY_THRESHOLD = 100;

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float distanceX = e2.getX() - e1.getX();
                    float distanceY = e2.getY() - e1.getY();
                    if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (distanceX > 0)
                            onSwipeRight();
                        else
                            onSwipeLeft();
                        return true;
                    }
                    return false;
                }
            }

        });

        buttonAdd = (Button)findViewById(R.id.buttonAddRation);
        buttonAdd.setOnClickListener(this);

        dbHelper = new DBHelper(this);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        String dateKey = calendar.get(Calendar.YEAR)+"."+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.DAY_OF_MONTH);//формат даты!
        Log.d("tagged", dateKey);
        Cursor cursor = dbHelper.getRationCursor(dateKey);

        if (cursor.moveToFirst())
        {

            int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
            int proteinColIndex = cursor.getColumnIndex(PROTEIN);
            int fatColIndex = cursor.getColumnIndex(FAT);
            int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
            int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
            int eatingColIndex = cursor.getColumnIndex(EATING);
            int timeColIndex = cursor.getColumnIndex(TIME);
            int dateColIndex = cursor.getColumnIndex(DATA);

            do {
                Map<String,Object> m = new HashMap<String, Object>();
                m.put(FOOD_NAME,cursor.getString(foodNameColIndex));
                m.put(PROTEIN,cursor.getFloat(proteinColIndex));
                m.put(FAT,cursor.getFloat(fatColIndex));
                m.put(CARBOHYDRATES,cursor.getFloat(carbohydratesColIndex));
                m.put(CALORIFIC,cursor.getFloat(calorificColIndex));
                m.put(EATING,cursor.getString(eatingColIndex));
                m.put(TIME,cursor.getString(timeColIndex));
                m.put(DATA, cursor.getString(dateColIndex));
                data.add(m);
                Log.d("food", (String) m.get(FOOD_NAME) + " " + (Float) m.get(PROTEIN) + " " + (Float) m.get(FAT) + " " + (Float) m.get(CARBOHYDRATES) + " " + (Float) m.get(CALORIFIC) + " " + (String) m.get(EATING) + " " + (String) m.get(TIME) + " " + (String) m.get(DATA));
                currentProt+=cursor.getFloat(proteinColIndex);
                currentFat+=cursor.getFloat(fatColIndex);
                currentCarbo+=cursor.getFloat(carbohydratesColIndex);
                currentCalor+=cursor.getFloat(calorificColIndex);
            } while(cursor.moveToNext());
        }

        tvProt.setText(getResources().getString(R.string.protein)+currentProt);
        tvFat.setText(getResources().getString(R.string.fat)+currentFat);
        tvCarbo.setText(getResources().getString(R.string.carbohydrates) + currentCarbo);
        tvCalor.setText(getResources().getString(R.string.calorifical) + currentCalor);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,R.layout.one_ration,from,to);
        listView.setAdapter(simpleAdapter);



    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Food.class);
        startActivityForResult(intent,REQUEST_CODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode==RESULT_OK)
        {
            String foodName = data.getStringExtra(FOOD_NAME);
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            Cursor cursor = database.query(FOOD_TABLE_NAME,
                    null,
                    FOOD_NAME+" LIKE ?",
                    new String[]{foodName},
                    null,
                    null,
                    null);
            if (cursor.moveToFirst()) {
                HashMap<String, Object> m = new HashMap<String, Object>();
                int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
                int proteinColIndex = cursor.getColumnIndex(PROTEIN);
                int fatColIndex = cursor.getColumnIndex(FAT);
                int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
                int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
                m.put(FOOD_NAME, cursor.getString(foodNameColIndex));
                m.put(PROTEIN, cursor.getDouble(proteinColIndex));
                m.put(FAT, cursor.getDouble(fatColIndex));
                m.put(CARBOHYDRATES, cursor.getDouble(carbohydratesColIndex));
                m.put(CALORIFIC, cursor.getDouble(calorificColIndex));



                AddRationFragment dialogFragment = new AddRationFragment();
                dialogFragment.addContext(this);
                dialogFragment.addFinishedListener(this);
                dialogFragment.addMap(m);
                dialogFragment.show(this.getFragmentManager(), "dialog");
            }
        }
    }


    @Override
    public void onAddingFoodFinished(int weight, int hour, int minute, String eating, HashMap<String,Object> foodData) {

        currentProt=0f;
        currentFat=0f;
        currentCarbo=0f;
        currentCalor=0f;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FOOD_NAME,(String)foodData.get(FOOD_NAME));
        cv.put(PROTEIN,((Double)foodData.get(PROTEIN)*weight)/100);
        cv.put(FAT,((Double)foodData.get(FAT)*weight)/100);
        cv.put(CARBOHYDRATES,((Double)foodData.get(FAT)*weight)/100);
        cv.put(CALORIFIC,((Double)foodData.get(CALORIFIC)*weight)/100);
        cv.put(EATING,eating);
        cv.put(TIME, hour + ":" + minute);
        Calendar calendar = Calendar.getInstance();
        cv.put(DATA,currentYear+"."+currentMonth+"."+currentDay);
        db.insert(RATION_TABLE_NAME,null,cv);



        String dateKey = currentYear+"."+currentMonth+"."+currentDay;//формат даты!
        //Log.d("tagged", dateKey);
        Cursor cursor = dbHelper.getRationCursor(dateKey);

        ArrayList<Map<String,Object>> tmpData = new ArrayList<Map<String, Object>>(10);

        if (cursor.moveToFirst())
        {

            int foodNameColIndex = cursor.getColumnIndex(FOOD_NAME);
            int proteinColIndex = cursor.getColumnIndex(PROTEIN);
            int fatColIndex = cursor.getColumnIndex(FAT);
            int carbohydratesColIndex = cursor.getColumnIndex(CARBOHYDRATES);
            int calorificColIndex = cursor.getColumnIndex(CALORIFIC);
            int eatingColIndex = cursor.getColumnIndex(EATING);
            int timeColIndex = cursor.getColumnIndex(TIME);
            int dateColIndex = cursor.getColumnIndex(DATA);

            do {
                Map<String,Object> m = new HashMap<String, Object>();
                m.put(FOOD_NAME,cursor.getString(foodNameColIndex));
                m.put(PROTEIN,cursor.getFloat(proteinColIndex));
                m.put(FAT,cursor.getFloat(fatColIndex));
                m.put(CARBOHYDRATES, cursor.getFloat(carbohydratesColIndex));
                m.put(CALORIFIC, cursor.getFloat(calorificColIndex));
                m.put(EATING, cursor.getString(eatingColIndex));
                m.put(TIME, cursor.getString(timeColIndex));
                m.put(DATA,cursor.getString(dateColIndex));
                tmpData.add(m);
                currentProt+=cursor.getFloat(proteinColIndex);
                currentFat+=cursor.getFloat(fatColIndex);
                currentCarbo+=cursor.getFloat(carbohydratesColIndex);
                currentCalor+=cursor.getFloat(calorificColIndex);

            } while(cursor.moveToNext());
        }

        tvProt.setText(getResources().getString(R.string.protein)+currentProt);
        tvFat.setText(getResources().getString(R.string.fat)+currentFat);
        tvCarbo.setText(getResources().getString(R.string.carbohydrates)+currentCarbo);
        tvCalor.setText(getResources().getString(R.string.calorifical)+currentCalor);

        dbHelper.addToGraph(calendar.get(Calendar.YEAR)+"."+calendar.get(Calendar.MONTH)+"."+calendar.get(Calendar.DAY_OF_MONTH),currentCalor,currentProt,currentFat,currentCarbo);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,tmpData,R.layout.one_ration,from,to);
        listView.setAdapter(simpleAdapter);
    }
}