package com.chandilsachin.diettracker.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.ui.FoodDiaryFragment

/**
 * Created by sachin on 3/6/17.
 */
class FoodDiaryAdapter(val activity:FragmentActivity, val fragmentManager: FragmentManager, var pageCount:Int) :
        FragmentStatePagerAdapter(fragmentManager){

    override fun getCount() = pageCount

    override fun getItem(p0: Int): Fragment {
        return FoodDiaryFragment.getInstance(Date past (p0 - pageCount + 1) , activity)
    }
}