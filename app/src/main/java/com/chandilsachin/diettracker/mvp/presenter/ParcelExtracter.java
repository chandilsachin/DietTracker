package com.chandilsachin.diettracker.mvp.presenter;

import android.os.Parcel;

/**
 * Created by BBI-M1025 on 15/06/16.
 */
public class ParcelExtracter
{
    private static final ClassLoader CLASS_LOADER = ParcelExtracter.class.getClassLoader();

    static <T> T unmarshall(byte[] array)
    {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(array,0,array.length);
        parcel.setDataPosition(0);
        Object value = parcel.readValue(CLASS_LOADER);
        parcel.recycle();
        return (T) value;
    }

    static byte[] marshall(Object o)
    {
        Parcel parcel = Parcel.obtain();
        parcel.writeValue(o);
        byte[] result = parcel.marshall();
        parcel.recycle();
        return result;
    }
}
