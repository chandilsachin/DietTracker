package com.chandilsachin.diettracker.util

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders

/**
 * Created by sachin on 24/5/17.
 */

fun <T:ViewModel> LifecycleActivity.initViewModel(c:Class<T>):T{
    val model = ViewModelProviders.of(this).get(c)
    return model
}