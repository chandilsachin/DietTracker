package com.chandilsachin.diettracker;

import android.content.Context;

import com.chandilsachin.diettracker.io.TemporaryDataManager;

/**
 * Created by Sachin Chandil on 01/05/2017.
 */

public class InitPreferences {
    private static final String PREFERENCE_NAME = "INIT_PREFERENCE";

    private static final String DATA_HAS_LOADED = "DATA_HAS_LOADED";

    TemporaryDataManager db;
    public InitPreferences(Context context){
        db = new TemporaryDataManager(context, PREFERENCE_NAME);
    }

    public void setDataHasLoaded(boolean value){
        db.setBoolean(DATA_HAS_LOADED, value);
        db.commit();
    }

    public boolean hasDataLoaded(){
        return db.getBoolean(DATA_HAS_LOADED);
    }
}
