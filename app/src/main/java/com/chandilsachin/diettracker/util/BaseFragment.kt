package com.chandilsachin.diettracker.util

import android.arch.lifecycle.LifecycleFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.chandilsachin.diettracker.util.annotation.RequiresTagName

/**
 * Created by sachin on 28/5/17.
 */
open class BaseFragment:LifecycleFragment(){

    companion object {
        val TAG_NAME = (BaseFragment::class.annotations.find { it == RequiresTagName::class } as? RequiresTagName).toString()

        fun findInstance(activity:FragmentActivity):Fragment?{
            return activity.supportFragmentManager.findFragmentByTag(TAG_NAME)
        }
    }
}