package com.chandilsachin.diettracker.mvp.annotation;

import com.chandilsachin.diettracker.mvp.initializer.InitMethodsInterface;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by BBI-1034 on 22/6/2016.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresInitializer {
    Class<? extends InitMethodsInterface> value();
}
