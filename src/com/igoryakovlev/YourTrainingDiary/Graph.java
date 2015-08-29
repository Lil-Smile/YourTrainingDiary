package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.Collection;


/**
 * Created by Smile on 21.08.15.
 */
public class Graph extends Activity implements Constants {

    private static DBHelper dbHelper;

    float totalCalor, totalCarbo, totalFat, totalProt;
    int days=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

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

            draw();

        } else
        {

        }


    }

    private void draw()
    {

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
        defaultRenderer.setChartTitle("БЖУ");
        defaultRenderer.setChartTitleTextSize(20);
        defaultRenderer.setZoomButtonsVisible(true);
        Intent intent = ChartFactory.getPieChartIntent(getApplicationContext(),categorySeries,defaultRenderer,"График");
        startActivity(intent);

    }

}