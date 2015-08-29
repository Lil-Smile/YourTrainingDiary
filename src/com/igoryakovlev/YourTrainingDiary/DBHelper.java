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
        createTableGraph(db);
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

    private void createTableGraph(SQLiteDatabase db)
    {
        Log.d("db","graph table ");
        String executionString = CREATE_TABLE+SPACE+GRAPH_TABLE_NAME+SPACE+LEFT_BRACKET+
                ID+SPACE+ID_TYPE+COMMA+SPACE+
                DATA+SPACE+TEXT_TYPE+COMMA+SPACE+
                CALORIFIC+SPACE+REAL_TYPE+COMMA+SPACE+
                PROTEIN+SPACE+REAL_TYPE+COMMA+SPACE+
                FAT+SPACE+REAL_TYPE+COMMA+SPACE+
                CARBOHYDRATES+SPACE+REAL_TYPE+RIGHT_BRACKET+SEMICOLON;
        db.execSQL(executionString);
        Log.d("GraphTabelCreated",executionString);
    }


    private void fillTableFood(SQLiteDatabase db)
    {
        Log.d("db","food filled");
        ContentValues cv = new ContentValues();
        cv.put(FOOD_NAME,"Вода");
        cv.put(PROTEIN,0f);
        cv.put(FAT,0f);
        cv.put(CARBOHYDRATES, 0f);
        cv.put(CALORIFIC, 0f);
        db.insert(FOOD_TABLE_NAME, null, cv);

    }

    public Cursor getFoodCursor() {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(FOOD_TABLE_NAME,null,null,null,null,null,null,null);
        Log.d("db", "cursor passed");
        return cursor;
    }

    public void addTheFoodToDatabase(String foodName, float protein, float fat, float carbohydrates, float calorifical)
    {
        ContentValues cv = new ContentValues();
        cv.put(FOOD_NAME,foodName);
        cv.put(PROTEIN,protein);
        cv.put(FAT,fat);
        cv.put(CARBOHYDRATES,carbohydrates);
        cv.put(CALORIFIC,calorifical);
        getWritableDatabase().insert(FOOD_TABLE_NAME, null, cv);
    }


    public Cursor getFoodCursorSearch(String key)
    {
        //Log.d("search",key);
        SQLiteDatabase database = this.getReadableDatabase();
        String selection=FOOD_NAME+" LIKE ?";
        String[] selectionArgs = {"%"+key+"%"};
        Log.d("search in db", selection + selectionArgs[0]);
        Cursor cursor = database.query(FOOD_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null);

        return cursor;
    }


    public Cursor getRationCursor(String date)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String selection = DATA + " LIKE ?";
        String[] selectionArgs = {date};
        Cursor cursor = database.query(RATION_TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);
        return  cursor;
    }

    public void addToGraph(String date, float calor, float prot, float fat, float carbo)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String selection = DATA+" LIKE ?";
        String[] selectionArgs = {date};
        ContentValues cv = new ContentValues();
        cv.put(CALORIFIC,calor);
        cv.put(CARBOHYDRATES,carbo);
        cv.put(FAT,fat);
        cv.put(PROTEIN,prot);
        cv.put(DATA,date);
        Cursor cursor = database.query(GRAPH_TABLE_NAME,null,selection,selectionArgs,null,null,null);
        if (cursor.moveToFirst())
        {
            database.update(GRAPH_TABLE_NAME,cv,selection,selectionArgs);
        } else
        {
            database.insert(GRAPH_TABLE_NAME,null,cv);
        }

    }

    public Cursor getGraphCursor()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(GRAPH_TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }




}
