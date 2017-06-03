package com.chandilsachin.diettracker.repository

import android.app.ProgressDialog
import android.arch.lifecycle.LiveData
import android.content.Context
import com.chandilsachin.diettracker.database.DietFood
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.other.AddFoodPresenter
import com.chandilsachin.diettracker.other.InitPreferences
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

/**
 * Created by sachin on 27/5/17.
 */
class MainActivityRepository{

    fun setUpFoodDatabase(context:Context){

        val list = AddFoodPresenter().readFoodItemsFile(context)
        FoodDatabase.getInstance(context).addFoodList(list)
    }

    fun getTodaysFoodList(context: Context):List<DietFood>{
        return FoodDatabase.getInstance(context).getFood(Date())
    }

    fun deleteDietFood(food:PersonalizedFood, context: Context){
        FoodDatabase.getInstance(context).deleteDietFood(food)
    }
}