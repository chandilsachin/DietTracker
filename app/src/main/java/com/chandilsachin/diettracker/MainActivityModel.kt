package com.chandilsachin.diettracker

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context

import com.chandilsachin.diettracker.Food
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import java.util.ArrayList
import java.util.Calendar
import java.util.Date

/**
 * Created by Sachin Chandil on 02/05/2017.
 */

class MainActivityModel(application: Application?) : AndroidViewModel(application) {
    val userLiveData: MutableLiveData<ArrayList<Food>> = MutableLiveData()

    init {
        doAsync {
            //val list = FoodDatabase.getInstance(baseContext)?.getFood(Calendar.getInstance().time.time)
            val list = DatabaseManager.getInstance(getApplication()).getFoodOnDate(Calendar.getInstance().time);
            userLiveData.value = list
        }
    }
    /*fun getDietList(context: Context): ArrayList<Food> {
        val today = Date(Calendar.getInstance().timeInMillis)
        return DatabaseManager.getInstance(context).getFoodOnDate(today)
    }*/
}
