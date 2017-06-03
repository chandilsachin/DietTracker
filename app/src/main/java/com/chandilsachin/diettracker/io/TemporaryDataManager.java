package com.chandilsachin.diettracker.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class TemporaryDataManager
{
    private static String PREFS_NAME;
    private Context context;
    private SharedPreferences prefs;
    private Editor editor;

    public TemporaryDataManager(Context context, String name)
    {
        PREFS_NAME = name;
        this.context = context;
        connectDB();
    }

    private void connectDB()
    {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void commit()
    {
        editor.commit();
    }

    public boolean containsKey(String key)
    {
        return prefs.contains(key);
    }

    public void setInt(String key, int value)
    {
        editor.putInt(key, value);
    }

    public int getInt(String key)
    {
        return prefs.getInt(key, 0);
    }

    public void setBoolean(String key, boolean value)
    {
        editor.putBoolean(key, value);
    }

    public boolean getBoolean(String key)
    {
        return prefs.getBoolean(key, false);
    }

    public void setString(String key, String value)
    {
        editor.putString(key, value);
    }

    public String getString(String key)
    {
        return prefs.getString(key, "");
    }

    public void setLong(String key, Long value)
    {
        editor.putLong(key, value);
    }

    public long getLong(String key)
    {
        return prefs.getLong(key, Long.valueOf("0"));
    }

    /**
     * Clears all entry in current shared preference
     */
    public void clearAll()
    {
        editor.clear();
        editor.commit();
    }
}
