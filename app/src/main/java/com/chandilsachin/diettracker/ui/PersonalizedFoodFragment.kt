package com.chandilsachin.diettracker.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.util.BaseFragment


class PersonalizedFoodFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_personalized_food, container, false)
    }

    companion object {

        fun getInstance(activity: FragmentActivity): Fragment {
            var fragment = findInstance(activity)
            if (fragment == null) {
                fragment = PersonalizedFoodFragment()
            }
            return fragment
        }
    }
}