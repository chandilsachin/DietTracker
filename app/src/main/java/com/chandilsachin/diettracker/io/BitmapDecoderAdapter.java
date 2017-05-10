package com.chandilsachin.diettracker.io;

import android.graphics.drawable.BitmapDrawable;


public class BitmapDecoderAdapter<E> implements BitmapDecoderTask.BitmapDecodeListener<E>
{

    @Override
    public void onBitmapDecodeComplete(BitmapDrawable bitmapDrawable)
    {

    }

    @Override
    public BitmapDrawable onBitmapDecode(E data)
    {
        return null;
    }

}
