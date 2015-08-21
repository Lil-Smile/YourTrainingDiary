package com.igoryakovlev.YourTrainingDiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        Log.d("db","inited");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db","created");
        createTableFood(db);
        createTableRation(db);
        fillTableFood(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTableRation(SQLiteDatabase db)
    {
        Log.d("db","ration created");
        String executionString = CREATE_TABLE+SPACE+RATION_TABLE_NAME+SPACE+LEFT_BRACKET+
                ID+SPACE+ID_TYPE+COMMA+SPACE+
                FOOD_NAME+SPACE+TEXT_TYPE+COMMA+SPACE+
                PROTEIN+SPACE+REAL_TYPE+COMMA+SPACE+
                FAT+SPACE+REAL_TYPE+COMMA+SPACE+
                CARBOHYDRATES+SPACE+REAL_TYPE+COMMA+SPACE+
                CALORIFIC+SPACE+REAL_TYPE+COMMA+SPACE+
                TIME+SPACE+TEXT_TYPE+COMMA+SPACE+
                DATA+SPACE+TEXT_TYPE+COMMA+SPACE+
                EATING+SPACE+TEXT_TYPE+
                RIGHT_BRACKET+SEMICOLON;
        db.execSQL(executionString);
        Log.d("RationTableCreated",executionString);


    }

    private void createTableFood(SQLiteDatabase db)
    {
        Log.d("db","food created");
        String executionString = CREATE_TABLE+SPACE+FOOD_TABLE_NAME+SPACE+LEFT_BRACKET+
                ID+SPACE+ID_TYPE+COMMA+SPACE+
                FOOD_NAME+SPACE+TEXT_TYPE+COMMA+SPACE+
                PROTEIN+SPACE+REAL_TYPE+COMMA+SPACE+
                FAT+SPACE+REAL_TYPE+COMMA+SPACE+
                CARBOHYDRATES+SPACE+REAL_TYPE+COMMA+SPACE+
                CALORIFIC+SPACE+REAL_TYPE+
                RIGHT_BRACKET+SEMICOLON;
        db.execSQL(executionString);
        Log.d("FoodTableCreated",executionString);


    }
    private void fillTableFood(SQLiteDatabase db)
    {
        Log.d("db","food filled");
        ContentValues cv = new ContentValues();
        cv.put(FOOD_NAME,"Вода");
        cv.put(PROTEIN,0f);
        cv.put(FAT,0f);
        cv.put(CARBOHYDRATES,0f);
        cv.put(CALORIFIC,0f);
        db.insert(FOOD_TABLE_NAME,null,cv);

    }

    public Cursor getFoodCursor() {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(FOOD_TABLE_NAME,null,null,null,null,null,null,null);
        Log.d("db","cursor passed");
        return cursor;
    }
}
