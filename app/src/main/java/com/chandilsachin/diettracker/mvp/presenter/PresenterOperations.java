package com.chandilsachin.diettracker.mvp.presenter;

import android.os.Bundle;
import android.view.View;

/**
 * Created by BBI-M1025 on 15/06/16.
 */

public interface PresenterOperations
{
    void onCreate(Bundle bundle);

    void onCreateView(View view);

    void onConfigurationChange();

    void onPause();

}
