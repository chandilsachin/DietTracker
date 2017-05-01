package com.chandilsachin.diettracker.io;

import android.content.Context;
import android.graphics.drawable.Drawable;


public class ImageGenerator
{
    public static Drawable generateImage(Context context, String imageName)
    {
        int imageid =
                context.getResources().getIdentifier(imageName, "drawable",
                        context.getPackageName());

        return context.getResources().getDrawable(imageid);
    }

    public static int generateImageResource(Context context, String imageName)
    {
        int imageid =
                context.getResources().getIdentifier(imageName, "drawable",
                        context.getPackageName());

        return imageid;
    }


}
