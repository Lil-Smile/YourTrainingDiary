package com.igoryakovlev.YourTrainingDiary;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

/**
 * Created by Smile on 30.08.15.
 */
public class Stat extends Activity implements Constants {

    TextView tvCalorRecom, tvCalorCurrent,
             tvCarboRecom, tvCarboCurrent,
             tvProtRecom, tvProtCurrent,
             tvFatRecom, tvFatCurrent;

    float calor,prot,carbo,fat,calorRecom,protRecom,carboRecom,fatRecom;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat);

        tvCalorCurrent=(TextView)findViewById(R.id.tvStatCalor);
        tvCarboCurrent=(TextView)findViewById(R.id.tvStatCarbo);
        tvProtCurrent=(TextView)findViewById(R.id.tvStatProt);
        tvFatCurrent=(TextView)findViewById(R.id.tvStatFat);
        tvCalorRecom=(TextView)findViewById(R.id.tvStatCalorRecom);
        tvCarboRecom=(TextView)findViewById(R.id.tvStatCarboRecom);
        tvProtRecom=(TextView)findViewById(R.id.tvStatProtRecom);
        tvFatRecom=(TextView)findViewById(R.id.tvStatFarRecom);

        calor=prot=carbo=fat=calorRecom=protRecom=carboRecom=fatRecom=0;


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        int gender = Integer.valueOf(sp.getString(getResources().getString(R.string.keyGender),"0")).intValue();
        switch (gender)
        {
            case 0:
            {
                tvCalorCurrent.setText(" ");tvCarboCurrent.setText(" ");tvFatCurrent.setText(" ");tvProtCurrent.setText(" ");
                tvCalorRecom.setText(" ");tvCarboRecom.setText(" ");tvFatRecom.setText(" ");tvProtRecom.setText(" ");
                break;
            }
            case 1: //man
            {
                int age = Integer.valueOf(sp.getString(getString(R.string.keyAge),"0")).intValue();
                int height = Integer.valueOf(sp.getString(getString(R.string.keyHeight),"0")).intValue();
                int weight = Integer.valueOf(sp.getString(getString(R.string.keyWeight),"0")).intValue();
                float koef = Float.valueOf(sp.getString(getString(R.string.keyKoef),"0")).floatValue();
                float result = (float)(10*weight+6.25*height-5*age+5);
                calorRecom=result*koef;
                int program = Integer.valueOf(sp.getString(getString(R.string.keyProgram),"0")).intValue();
                switch (program)
                {
                    case 0: //wrong
                    {
                        tvCalorCurrent.setText(" ");tvCarboCurrent.setText(" ");tvFatCurrent.setText(" ");tvProtCurrent.setText(" ");
                        tvCalorRecom.setText(" ");tvCarboRecom.setText(" ");tvFatRecom.setText(" ");tvProtRecom.setText(" ");
                        break;
                    }
                    case 1: //набор
                    {
                        calorRecom*=1.15;
                        protRecom=weight*2.5f;
                        carboRecom=weight*5;
                        fatRecom=(calorRecom-protRecom*4-carboRecom*4)/8;
                        tvCarboRecom.setText((int)carboRecom+" г"); tvCalorRecom.setText((int)calorRecom+" кк"); tvProtRecom.setText((int)protRecom+" г"); tvFatRecom.setText((int)fatRecom+" г");
                        break;
                    }
                    case 2: //похудение
                    {
                        calorRecom*=0.85;
                        protRecom=(calorRecom*0.3f)/4;
                        fatRecom=(calorRecom*0.2f)/8;
                        carboRecom=(calorRecom*0.5f)/4;
                        tvCarboRecom.setText((int)carboRecom+" г"); tvCalorRecom.setText((int)calorRecom+" кк"); tvProtRecom.setText((int)protRecom+" г"); tvFatRecom.setText((int)fatRecom+" г");
                        break;
                    }
                    case 3: //поддержание
                    {

                        protRecom=(calorRecom*0.2f)/4;
                        fatRecom=(calorRecom*0.2f)/8;
                        carboRecom=(calorRecom*0.6f)/4;
                        tvCarboRecom.setText((int)carboRecom+" г"); tvCalorRecom.setText((int)calorRecom+" кк"); tvProtRecom.setText((int)protRecom+" г"); tvFatRecom.setText((int)fatRecom+" г");
                        break;
                    }

                }
                break;
            }
            case 2:
            {
                int age = Integer.valueOf(sp.getString(getString(R.string.keyAge),"0")).intValue();
                int height = Integer.valueOf(sp.getString(getString(R.string.keyHeight),"0")).intValue();
                int weight = Integer.valueOf(sp.getString(getString(R.string.keyWeight),"0")).intValue();
                float koef = Float.valueOf(sp.getString(getString(R.string.keyKoef),"0")).floatValue();
                float result = (float)(10*weight+6.25*height-5*age-161);
                calorRecom=result*koef;
                int program = Integer.valueOf(sp.getString(getString(R.string.keyProgram),"0")).intValue();
                switch (program)
                {
                    case 0: //wrong
                    {
                        tvCalorCurrent.setText(" ");tvCarboCurrent.setText(" ");tvFatCurrent.setText(" ");tvProtCurrent.setText(" ");
                        tvCalorRecom.setText(" ");tvCarboRecom.setText(" ");tvFatRecom.setText(" ");tvProtRecom.setText(" ");
                        break;
                    }
                    case 1: //набор
                    {
                        calorRecom*=1.15;
                        protRecom=weight*2.5f;
                        carboRecom=weight*5;
                        fatRecom=(calorRecom-protRecom*4-carboRecom*4)/8;
                        tvCarboRecom.setText((int)carboRecom+" г"); tvCalorRecom.setText((int)calorRecom+" кк"); tvProtRecom.setText((int)protRecom+" г"); tvFatRecom.setText((int)fatRecom+" г");
                        break;
                    }
                    case 2: //похудение
                    {
                        calorRecom*=0.85;
                        protRecom=(calorRecom*0.3f)/4;
                        fatRecom=(calorRecom*0.2f)/8;
                        carboRecom=(calorRecom*0.5f)/4;
                        tvCarboRecom.setText((int)carboRecom+" г"); tvCalorRecom.setText((int)calorRecom+" кк"); tvProtRecom.setText((int)protRecom+" г"); tvFatRecom.setText((int)fatRecom+" г");
                        break;
                    }
                    case 3: //поддержание
                    {

                        protRecom=(calorRecom*0.2f)/4;
                        fatRecom=(calorRecom*0.2f)/8;
                        carboRecom=(calorRecom*0.6f)/4;
                        tvCarboRecom.setText((int)carboRecom+" г"); tvCalorRecom.setText((int)calorRecom+" кк"); tvProtRecom.setText((int)protRecom+" г"); tvFatRecom.setText((int)fatRecom+" г");
                        break;
                    }

                }
                break;
            }
        }

        float totalProt, totalFat,totalCarbo,totalCalor;
        int days;
        days = 0;
        totalCalor=totalCarbo=totalFat=totalProt=0;
        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getGraphCursor();
        int protColIndex = cursor.getColumnIndex(PROTEIN);
        int fatColIndex = cursor.getColumnIndex(FAT);
        int carboColIndex = cursor.getColumnIndex(CARBOHYDRATES);
        int calorColIndex = cursor.getColumnIndex(CALORIFIC);

        if (cursor.moveToFirst()) {
            do {
                totalProt += cursor.getFloat(protColIndex);
                totalFat += cursor.getFloat(fatColIndex);
                totalCarbo += cursor.getFloat(carboColIndex);
                totalCalor += cursor.getFloat(calorColIndex);
                days++;
            } while (cursor.moveToNext());
        }
        if(days==0)
        {
            tvCalorCurrent.setText("0 г");tvCarboCurrent.setText("0 г");tvFatCurrent.setText("0 г");tvProtCurrent.setText("0 г");
        } else
        {
            prot=totalProt/days;
            calor=totalCalor/days;
            carbo=totalCarbo/days;
            fat=totalFat/days;
            tvCalorCurrent.setText((int)calor+" г");tvCarboCurrent.setText((int)carbo+" г");tvFatCurrent.setText((int)fat+" г");tvProtCurrent.setText((int)prot+" г");
        }



    }
}