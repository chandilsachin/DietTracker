package com.chandilsachin.diettracker;

import com.ne1c.rainbowmvp.base.BasePresenter;

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

public class PresenterStorage implements com.ne1c.rainbowmvp.PresenterStorage {
    @Override
    public BasePresenter create(String s) {
        if(s.equals(AddFoodPresenter.TAG))
            return new AddFoodPresenter();
        return null;
    }
}
