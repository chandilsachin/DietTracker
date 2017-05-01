package com.chandilsachin.diettracker.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chandilsachin.diettracker.mvp.annotation.RequiresPresenter;


/**
 * Created by BBI-M1025 on 15/06/16.
 */
public class PresenterFactory<P extends Presenter>
{
    private Class<P> presenterClass;
    @Nullable private Bundle bundle;

    public PresenterFactory(Class<P> presenterClass)
    {
        this.presenterClass = presenterClass;
    }

    public static<P extends Presenter> PresenterFactory<P> getPresenterClass(Class<?> viewClass)
    {
        RequiresPresenter annotation = viewClass.getAnnotation(RequiresPresenter.class);
        if(annotation == null)
            throw new RuntimeException("Class must have RequiresPresenter annotation.");
        Class<P> presenterClass = annotation == null ? null : (Class<P>) annotation.value();
        return presenterClass == null ? null : new PresenterFactory<>(presenterClass);
    }



    public P createPresenter()
    {
        try
        {
            return presenterClass.newInstance();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
