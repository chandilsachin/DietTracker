package com.chandilsachin.diettracker.io;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

public final class FontsOverride
{

    public static void populateFonts(Activity activity, HashMap<String, String> fontTable)
    {
        if (fontTable != null)
        {
            Iterator<String> fonts = fontTable.keySet().iterator();

            while (fonts.hasNext())
            {
                String font = fonts.next();
                setDefaultFont(activity, font, "fonts/" + fontTable.get(font));
            }
        }
    }

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName)
    {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface)
    {
        try
        {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}