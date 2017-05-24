package com.chandilsachin.diettracker

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import org.jetbrains.anko.doAsync
import java.util.*

/**
 * Created by sachin on 24/5/17.
 */
class FoodDbLiveData(context: Context):MutableLiveData<List<Food>>() {
    init {
        doAsync {
            //value = DatabaseManager.getInstance(context).getFoodOnDate(Calendar.getInstance().time)
            //value =
        }
    }
}