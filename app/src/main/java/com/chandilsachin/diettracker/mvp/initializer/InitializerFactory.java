package com.chandilsachin.diettracker.mvp.initializer;

import com.chandilsachin.diettracker.mvp.annotation.RequiresInitializer;

/**
 * Created by BBI-1034 on 22/6/2016.
 */
public class InitializerFactory<I extends InitMethodsInterface> {

    private Class<I> initializerClass;

    public InitializerFactory(Class<I> initializerClass) {
        this.initializerClass = initializerClass;
    }

    public static<I extends InitMethodsInterface> InitializerFactory<I> getInitializerClass(Class<?> viewClass)
    {
        RequiresInitializer annotation = viewClass.getAnnotation(RequiresInitializer.class);
        if(annotation == null)
            throw new RuntimeException("Class must have RequiresInitializer annotation.");
        Class<I> initializerClass = annotation == null ? null : (Class<I>) annotation.value();
        return initializerClass == null ? null : new InitializerFactory<>(initializerClass);
    }

    /**
     * Creates instance of InitMethodInterface interface
     * @return
     */
    public I getInitializer()
    {
        try {
            return initializerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
