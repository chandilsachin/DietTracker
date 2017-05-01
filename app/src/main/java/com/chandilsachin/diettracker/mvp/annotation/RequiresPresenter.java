package com.chandilsachin.diettracker.mvp.annotation;

import com.chandilsachin.diettracker.mvp.presenter.PresenterOperations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by BBI-M1025 on 15/06/16.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends PresenterOperations> value();
}
