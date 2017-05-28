package com.chandilsachin.diettracker.util

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by sachin on 28/5/17.
 */
fun <T: ViewModel> Fragment.initViewModel(c:Class<T>):T{
    val model = ViewModelProviders.of(this).get(c)
    return model
}

fun Fragment.loadFragment(containerId:Int, fragment: Fragment){
    loadFragment(containerId, fragment, activity as AppCompatActivity)
}

internal fun loadFragment(containerId:Int, fragment: Fragment, activity: AppCompatActivity){
    activity.supportFragmentManager.beginTransaction().replace(containerId, fragment)
            .addToBackStack(null).commit()
}