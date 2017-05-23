package com.chandilsachin.diettracker;

import android.content.Context;

import com.chandilsachin.diettracker.database.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sachin Chandil on 02/05/2017.
 */

public class MainPresenter {

    public ArrayList<Food> getDietList(Context context){
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        return DatabaseManager.getInstance(context).getFoodOnDate(today);
    }
}
