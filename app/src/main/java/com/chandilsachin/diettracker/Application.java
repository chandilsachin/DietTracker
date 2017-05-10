package com.chandilsachin.diettracker;

import com.ne1c.rainbowmvp.PresenterFactory;

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //PresenterFactory.init(new PresenterStorage());
    }
}
