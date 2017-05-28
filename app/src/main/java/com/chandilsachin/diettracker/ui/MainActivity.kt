package com.chandilsachin.diettracker.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.util.loadFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load FoodDiaryFragment with current date
        loadFragment(R.id.frameLayoutFragment, FoodDiaryFragment.getInstance(activity = this as FragmentActivity))
    }
}
