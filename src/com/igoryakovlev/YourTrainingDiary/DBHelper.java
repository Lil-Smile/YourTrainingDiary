package com.igoryakovlev.YourTrainingDiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Smile on 21.08.15.
 */
public class DBHelper extends SQLiteOpenHelper implements Constants{

    private static int DATABASE_VERSION=1;

    //public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //super(context, name, factory, version);
    //}

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableFood(db);
        createTableRation(db);
        fillTableFood(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTableRation(SQLiteDatabase db)
    {
        String executionString = CREATE_TABLE+SPACE+RATION_TABLE_NAME+SPACE+LEFT_BRACKET+
                ID+SPACE+ID_TYPE+COMMA+SPACE+
                FOOD_NAME+TEXT_TYPE+COMMA+SPACE+
                PROTEIN+REAL_TYPE+COMMA+SPACE+
                FAT+REAL_TYPE+COMMA+SPACE+
                CARBOHYDRATES+REAL_TYPE+COMMA+SPACE+
                CALORIFIC+REAL_TYPE+COMMA+SPACE+
                TIME+TEXT_TYPE+COMMA+SPACE+
                DATA+TEXT_TYPE+COMMA+SPACE+
                EATING+TEXT_TYPE+
                RIGHT_BRACKET+SEMICOLON;
        db.execSQL(executionString);
        Log.d("RationTableCreated",executionString);


    }

    private void createTableFood(SQLiteDatabase db)
    {
        String executionString = CREATE_TABLE+SPACE+FOOD_TABLE_NAME+SPACE+LEFT_BRACKET+
                ID+SPACE+ID_TYPE+COMMA+SPACE+
                FOOD_NAME+TEXT_TYPE+COMMA+SPACE+
                PROTEIN+REAL_TYPE+COMMA+SPACE+
                FAT+REAL_TYPE+COMMA+SPACE+
                CARBOHYDRATES+REAL_TYPE+COMMA+SPACE+
                CALORIFIC+REAL_TYPE+
                RIGHT_BRACKET+SEMICOLON;
        db.execSQL(executionString);
        Log.d("FoodTableCreated",executionString);


    }
    private void fillTableFood(SQLiteDatabase db)
    {}

}
