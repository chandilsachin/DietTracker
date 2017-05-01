package com.chandilsachin.diettracker.mvp.presenter;

import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by BBI-M1025 on 15/06/16.
 */
public class Presenter<View> implements PresenterOperations
{

    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();


    @Override
    public void onCreate(Bundle bundle)
    {
    }

    @Override
    public void onCreateView(android.view.View view)
    {

    }

    @Override
    public void onConfigurationChange()
    {

    }

    @Override
    public void onPause()
    {

    }


    /**
     * Called when Presenter is about to destroy. bundle is passed to new presenter instance after process restart.
     * @param bundle
     */
    public void onSave(Bundle bundle)
    {

    }

    public void onDropView()
    {

    }


    /**
     * This method gets called when user leaves view.
     */
    public void onDestroy()
    {

    }

    /**
     * A Callback to be invoked when a fragment is about to destroy.
     */
    public interface OnDestroyListener{

        void onDestroy();
    }

    public void addOnDestroyListener(OnDestroyListener listener)
    {
        onDestroyListeners.add(listener);
    }

    public void removeOnDestroyListener(OnDestroyListener listener)
    {
        onDestroyListeners.remove(listener);
    }


}
