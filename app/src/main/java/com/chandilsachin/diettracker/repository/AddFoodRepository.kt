package com.chandilsachin.diettracker.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase

/**
 * Created by sachin on 27/5/17.
 */
class AddFoodRepository{

    fun getAllFodList(context: Context):LiveData<List<Food>>{
        return FoodDatabase.getInstance(context).getAllFoodList()
    }
}