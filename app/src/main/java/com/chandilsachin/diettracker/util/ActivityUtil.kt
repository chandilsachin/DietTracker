package com.chandilsachin.diettracker.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by sachin on 24/5/17.
 */
fun <T:ViewModel> AppCompatActivity.initViewModel(c:Class<T>):T{
    val model = ViewModelProviders.of(this).get(c)
    return model
}

fun AppCompatActivity.loadFragment(containerId:Int, fragment:Fragment){
    loadFragment(containerId, fragment, this)
}

