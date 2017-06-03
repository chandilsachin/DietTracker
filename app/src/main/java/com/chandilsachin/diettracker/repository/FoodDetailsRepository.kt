package com.chandilsachin.diettracker.repository

import android.content.Context
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.model.Date

/**
 * Created by sachin on 27/5/17.
 */
class FoodDetailsRepository {

    fun getFoodDetails(context: Context, foodId:Long):Food{
        return FoodDatabase.getInstance(context).getFoodDetails(foodId)
    }

    fun saveFood(context: Context, food:PersonalizedFood){
        FoodDatabase.getInstance(context).saveFood(food)
    }

    fun updateFood(context: Context, food:PersonalizedFood){
        val tempFood = FoodDatabase.getInstance(context).getFood(food.foodId, Date())
        if(tempFood != null)
            food.quantity += tempFood.quantity
        FoodDatabase.getInstance(context).saveFood(food)
    }

}