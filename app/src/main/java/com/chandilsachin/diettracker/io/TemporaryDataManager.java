package com.chandilsachin.diettracker.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * <h1>public class</h1>
 * <p>This class uses <b>SharedPreferences</b> to save data in a private mode.</p>
 *
 * @author Kapil lokhande
 */
public class TemporaryDataManager
{
    private static String PREFS_NAME;
    private Context context;
    private SharedPreferences prefs;
    private Editor editor;

    /**
     * <h1>BBI Docs</h1>
     * <h1>public TemporaryDataManager(Context context)</h1>
     * <p> Constrator </p>
     *
     * @param context - context of current activity.
     * @param name    - name of SharedPreference.
     */
    public TemporaryDataManager(Context context, String name)
    {
        PREFS_NAME = name;
        this.context = context;
        connectDB();
    }

    /**
     * <h1>BBI Docs</h1>
     * <h1>public void connectDB()</h1>
     * <p>gets a SharedPreferences and puts it is edit mode.</p>
     */
    private void connectDB()
    {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    /**
     * <h1>BBI Docs</h1>
     * <h1>public void commit()</h1>
     * <p>commits the data that has been set</p>
     */
    public void commit()
    {
        editor.commit();
    }

    public boolean containsKey(String key)
    {
        return prefs.contains(key);
    }

    /**
     * <h1>BBI Docs</h1>
     * <h1>public void setInt(String key,int value)</h1>
     * <p>Sets an integer value with a key value</p>
     *
     * @param key   - a String value that is later used to find associated value.
     * @param value - int value to be saved.
     */
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
