package com.chandilsachin.diettracker.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by BBI-M1025 on 15/06/16.
 */
public class PresenterLifeHandler<P extends Presenter>
{
    private static final String PRESENTER_BUNDLE_KEY = "presenter";
    private static final String PRESENTER_ID_KEY = "presenter_id";

    @Nullable private PresenterFactory<P> presenterFactory;
    @Nullable private P presenter;
    @Nullable private Bundle bundle;

    private boolean presenterHasView;

    public PresenterLifeHandler(@Nullable PresenterFactory<P> presenterFactory)
    {
        this.presenterFactory = presenterFactory;
    }

    @Nullable
    public PresenterFactory<P> getPresenterFactory()
    {
        return presenterFactory;
    }

    public void setPresenterFactory(@Nullable PresenterFactory<P> presenterFactory)
    {
        if(this.presenterFactory != null)
            throw new IllegalArgumentException("setPresenterFactory should be called begore onResume()");
        this.presenterFactory = presenterFactory;
    }

    public P getPresenter()
    {
        if(presenterFactory != null)
        {
            if(presenter == null && bundle != null)
            {
                presenter = PresenterStorage.INSTANCES.getPresenter(bundle.getString(PRESENTER_ID_KEY));
            }

            if(presenter == null)
            {
                presenter = presenterFactory.createPresenter();
                PresenterStorage.INSTANCES.add(presenter);
                presenter.onCreate(bundle == null ? null : bundle.getBundle(PRESENTER_BUNDLE_KEY));
            }
        }
        return presenter;
    }

    public Bundle onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        getPresenter();
        if(presenter != null)
        {
            Bundle presenterBundle = new Bundle();
            presenter.onSave(presenterBundle);
            bundle.putBundle(PRESENTER_BUNDLE_KEY,presenterBundle);
            bundle.putString(PRESENTER_ID_KEY,PresenterStorage.INSTANCES.getId(presenter));
        }
        return bundle;
    }

    public void onRestoreInstanceState(Bundle presenterState)
    {
        if(presenter != null)
        {
            throw new IllegalArgumentException("onRestoreInstanceState() should be called before onResume()");
        }
        this.bundle = ParcelExtracter.unmarshall(ParcelExtracter.marshall(presenterState));
        if(presenter != null)
            presenter.onCreate(bundle);
    }

    public void onResume(Object view)
    {
        getPresenter();
        if(presenter != null && !presenterHasView)
        {
            presenter.onDropView();
            presenterHasView = false;
        }
    }

    public void onDestroy(boolean isFinal)
    {
        if(presenter != null && isFinal)
        {
            presenter.onDestroy();
            presenter = null;
        }
    }

}
