package com.chandilsachin.diettracker.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chandilsachin.diettracker.mvp.initializer.InitMethodsInterface;
import com.chandilsachin.diettracker.mvp.presenter.Presenter;
import com.chandilsachin.diettracker.mvp.presenter.PresenterFactory;
import com.chandilsachin.diettracker.mvp.presenter.PresenterLifeHandler;

/**
 * Created by BBI-M1025 on 15/06/16.
 */
public abstract class MVPDialogFragment<P extends Presenter> extends DialogFragment implements InitMethodsInterface
{
    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private PresenterLifeHandler<P> presenterDelegate = new PresenterLifeHandler<>(PresenterFactory.<P>getPresenterClass(getClass()));

    public PresenterFactory<P> getPresenterFactory()
    {
        return presenterDelegate.getPresenterFactory();
    }

    public void setPresenterFactory(PresenterFactory<P> presenterFactory)
    {
        this.presenterDelegate.setPresenterFactory(presenterFactory);
    }

    public P getPresenter()
    {
        return presenterDelegate.getPresenter();
    }

//    private InitMethodsInterface initMethodsInterface;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
        {
            presenterDelegate.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_STATE_KEY));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, presenterDelegate.onSaveInstanceState());
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    /**
     * Note: Call onCreateView(View) method in this method. This method triggers the events call of fragment initialization.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    boolean onCreateCalled = false;

    /**
     * Call this method in onCreateView. This method triggers the events call of fragment initialization.
     * @param
     */
    public View onCreateView(View v)
    {
        onCreateCalled = true;
        init(v);
        initData();
        setEvents();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!onCreateCalled)
            throw new RuntimeException("super.onCreateView(View) must be called in onCreteView(LayoutInflater,ViewGroup,Bundle) method.");
        presenterDelegate.getPresenter().onCreateView(view);

    }

    private void initData()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initializeData();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDrawConfig();
                        setInitConfig();
                    }
                });

            }
        }).start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        presenterDelegate.getPresenter().onPause();
    }


    @Override
    public void onResume()
    {
        super.onResume();
        presenterDelegate.onResume(this);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        presenterDelegate.onDestroy(!getActivity().isChangingConfigurations());
    }

    private ProgressDialog progressDialog;

    protected void dismissProgress()
    {
        if(progressDialog != null)
            progressDialog.dismiss();

    }

    protected void showProgress(String title, String message)
    {
        if(progressDialog != null && !progressDialog.isShowing())
        {
            progressDialog.show();
        }else
        {
            progressDialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER, title, message);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
    }
}
