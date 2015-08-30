package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class MyActivity extends Activity implements View.OnClickListener,Constants {
    /**
     * Called when the activity is first created.
     */

    Button buttonRation;
    Button buttonFood;
    Button buttonGraph;
    Button buttonSettings;
    Button buttonStat;

    DBHelper dbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        buttonRation = (Button)findViewById(R.id.buttonRation);
        buttonFood = (Button)findViewById(R.id.buttonFood);
        buttonGraph = (Button)findViewById(R.id.buttonGraph);
        buttonSettings = (Button)findViewById(R.id.buttonSettings);
        buttonStat = (Button)findViewById(R.id.buttonStat);

        buttonRation.setOnClickListener(this);
        buttonFood.setOnClickListener(this);
        buttonGraph.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
        buttonStat.setOnClickListener(this);

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

                float totalCalor, totalCarbo, totalFat, totalProt;
                int days=0;

                totalCalor=totalCarbo=totalFat=totalProt=0;

                dbHelper=new DBHelper(this);

                Cursor cursor = dbHelper.getGraphCursor();

                int protColIndex = cursor.getColumnIndex(PROTEIN);
                int fatColIndex = cursor.getColumnIndex(FAT);
                int carboColIndex = cursor.getColumnIndex(CARBOHYDRATES);
                int calorColIndex = cursor.getColumnIndex(CALORIFIC);

                if (cursor.moveToFirst())
                {
                    do {
                        totalProt += cursor.getFloat(protColIndex);
                        totalFat += cursor.getFloat(fatColIndex);
                        totalCarbo += cursor.getFloat(carboColIndex);
                        totalCalor += cursor.getFloat(calorColIndex);
                        days++;
                    } while (cursor.moveToNext());

                    String[] names = {"Белки","Жиры","Углеводы"};
                    float[] results = {totalProt/days,totalFat/days,totalCarbo/days};
                    int[] colors= {Color.RED,Color.GREEN,Color.BLUE};

                    CategorySeries categorySeries = new CategorySeries("БЖУ");
                    for (int i = 0; i<3; i++)
                    {
                        categorySeries.add(names[i],results[i]);
                    }
                    DefaultRenderer defaultRenderer = new DefaultRenderer();
                    for (int i = 0; i<3; i++)
                    {
                        SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
                        simpleSeriesRenderer.setColor(colors[i]);

                        defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);
                    }
                    defaultRenderer.setChartTitle("Белки Жиры Углеводы");
                    defaultRenderer.setChartTitleTextSize(40);
                    defaultRenderer.setLegendTextSize(40);
                    defaultRenderer.setLabelsTextSize(40);
                    //defaultRenderer.setZoomButtonsVisible(true);
                    intent = ChartFactory.getPieChartIntent(getApplicationContext(), categorySeries, defaultRenderer, "График");

                }
                else
                {
                    intent = new Intent(this,Graph.class);
                }
                break;
            }
            case R.id.buttonSettings:
            {
                intent = new Intent(this,Pref.class);
                break;
            }
            case R.id.buttonStat:
            {
                intent = new Intent(this, Stat.class);
                break;
            }
        }
        if (intent!=null)
        {
            startActivity(intent);
        }
    }
}
