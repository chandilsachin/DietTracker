package com.chandilsachin.diettracker.io;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapDecoderTask<E> extends AsyncTask<String, Void, BitmapDrawable>
{

    private Context context;
    private String key;
    private BitmapDecodeListener<E> onBitmapDecoded;
    private E base64;

    /**
     * This construct is used to sets bitmaps to views.
     *
     * @param context
     */
    public BitmapDecoderTask(Context context, BitmapDecodeListener onBitmapDecoded)
    {

        this.context = context;
        this.onBitmapDecoded = onBitmapDecoded;
    }

    /**
     * This construct is used to sets bitmaps from base64 content to views.
     *
     * @param context
     */
    public BitmapDecoderTask(Context context, E base64, BitmapDecodeListener<E> onBitmapDecoded)
    {

        this.context = context;
        this.onBitmapDecoded = onBitmapDecoded;
        this.base64 = base64;
    }

    /**
     * This construct is used to save bitmaps for further use.
     *
     * @param context
     */
    public <E extends ImageView> BitmapDecoderTask(Context context)
    {

        this.context = context;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

    }

    @Override
    protected BitmapDrawable doInBackground(String... arg0)
    {

        BitmapDrawable d;
        if (arg0.length < 1)
        {
            return onBitmapDecoded.onBitmapDecode(base64);
        } else if (arg0.length > 1)
            d = BitmapsManager.decodeSampledBitmapDrawableInDp(context.getResources(), arg0[0], Integer.parseInt(arg0[1]));
        else
            d = BitmapsManager.decodeSampledBitmapDrawable(context.getResources(), arg0[0]);

        return d;
    }

    @Override
    protected void onPostExecute(BitmapDrawable result)
    {

        super.onPostExecute(result);
        onBitmapDecoded.onBitmapDecodeComplete(result);


    }


    public interface BitmapDecodeListener<E>
    {
        /**
         * Called when bitmap is decoded.
         *
         * @param bitmapDrawable
         */
        public void onBitmapDecodeComplete(BitmapDrawable bitmapDrawable);

        /**
         * This method is executed in doInBackground callback. It contains set of code that is to be run in thread.
         */
        public BitmapDrawable onBitmapDecode(E data);
    }
}
