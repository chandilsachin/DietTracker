package com.chandilsachin.diettracker.other;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Sachin Chandil on 01/05/2017.
 */
@Deprecated
public class DatabaseManager extends SQLiteOpenHelper {

    private static final int VERSION_NO = 1;
    private static final String DATABASE_NAME = "diet";

    private static DatabaseManager instance;

    private static DatabaseManager getInstance(Context context){
        if(instance == null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ALL_FOOD_LIST);
        db.execSQL(CREATE_TABLE_PERSONALISED_FOOD_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ALL_FOOD_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + PERSONALISED_FOOD_LIST);
    }



    public void addFoodList(ArrayList<Food> list) {
        SQLiteDatabase db = getWritableDatabase();
        Iterator<Food> iterator = list.iterator();
        while (iterator.hasNext()) {
            Food food = iterator.next();
            ContentValues values = new ContentValues();
            values.put(NAME, food.getFoodName());
            values.put(DESC, food.getFoodDesc());
            values.put(PROTEIN, food.getProtein());
            values.put(CARBS, food.getCarbs());
            values.put(FAT, food.getFat());
            db.insert(ALL_FOOD_LIST, null, values);
        }
        db.close();

    }

    public Food getFoodDetails(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ALL_FOOD_LIST, null, ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Food food = null;
        if (cursor.moveToFirst()) {
            food = new Food(cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));
        }
        return food;
    }

    public ArrayList<Food> getAllFoodList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ALL_FOOD_LIST, null, null, null, null, null, null);
        ArrayList<Food> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food(cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));
                food.setId(cursor.getInt(0));
                list.add(food);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Food> getFoodOnDate(Date date) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + ALL_FOOD_LIST + " where " + ID + " in " +
                "(select " + FOOD_ID + " from " + PERSONALISED_FOOD_LIST + " where date = ?)", new String[]{toYYYYMMDD(date)});
        ArrayList<Food> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food(cursor.getString(1), cursor.getString(2), cursor.getFloat(3), cursor.getFloat(4), cursor.getFloat(5));
                list.add(food);
            } while (cursor.moveToNext());
        }
        db.close();
        return list;
    }

    public int getFoodQuantityOnDate(Long foodId, Date date) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select "+QUANTITY+" from " + PERSONALISED_FOOD_LIST + " where food_id = ? and date = ?", new String[]{foodId.toString(), toYYYYMMDD(date)});

        int quantity = -1;
        if (cursor.moveToFirst()) {
            quantity = cursor.getInt(0);
        }
        db.close();
        return quantity;
    }

    public boolean saveFood(long foodId, float quantity, Date date) {
        boolean res = false;
        int q = getFoodQuantityOnDate(foodId, date);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOOD_ID, foodId);
        values.put(QUANTITY, quantity + (q > 0?q:0));
        values.put(DATE, toYYYYMMDD(date));
        res = db.insertWithOnConflict(PERSONALISED_FOOD_LIST, null, values, SQLiteDatabase.CONFLICT_REPLACE) != -1;
        db.close();
        return res;
    }

    public boolean deleteFood(int foodId, Date date) {
        boolean res = false;
        SQLiteDatabase db = getWritableDatabase();
        res = db.delete(PERSONALISED_FOOD_LIST, FOOD_ID + "=? and " + DATE + "=?", new String[]{String.valueOf(foodId), toYYYYMMDD(date)}) != -1;
        return res;
    }

    public boolean updateFood(int foodId, float quantity, Date date) {
        boolean res = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(FOOD_ID, foodId);
        values.put(QUANTITY, quantity);
        values.put(DATE, toYYYYMMDD(date));
        res = db.update(PERSONALISED_FOOD_LIST, values, FOOD_ID + "=?", new String[]{String.valueOf(foodId)}) != -1;
        return res;
    }

    private String toYYYYMMDD(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/DD");
        return format.format(date);
    }


    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PROTEIN = "protein";
    private static final String DESC = "desc";
    private static final String CARBS = "carbs";
    private static final String FAT = "fat";

    private static final String ALL_FOOD_LIST = "all_food_list";
    private static final String CREATE_TABLE_ALL_FOOD_LIST = "create table " + ALL_FOOD_LIST + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            NAME + " TEXT," +
            DESC + " TEXT," +
            PROTEIN + " REAL," +
            CARBS + " REAL," +
            FAT + " REAL," +
            "CONSTRAINT name_unique UNIQUE ("+NAME+")" +
            ")";


    private static final String FOOD_ID = "food_id";
    private static final String QUANTITY = "quantity";
    private static final String DATE = "date";

    private static final String PERSONALISED_FOOD_LIST = "personalized_food_list";
    private static final String CREATE_TABLE_PERSONALISED_FOOD_LIST = "create table " + PERSONALISED_FOOD_LIST + "(" +
            FOOD_ID + " INTEGER," +
            QUANTITY + " quantity REAL," +
            DATE + " TEXT," +
            "PRIMARY KEY ("+FOOD_ID+", "+DATE+")," +
            "FOREIGN KEY("+FOOD_ID+") REFERENCES "+ALL_FOOD_LIST+"("+ID+")" +
            ")";
}