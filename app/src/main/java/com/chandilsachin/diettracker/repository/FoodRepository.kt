package com.chandilsachin.diettracker.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.chandilsachin.diettracker.database.DietFood
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.other.AddFoodPresenter

/**
 * Created by sachin on 27/5/17.
 */
class FoodRepository {

    fun getAllFodList(context: Context):LiveData<List<Food>>{
        return FoodDatabase.getInstance(context).getAllFoodList()
    }

    fun getFoodDetails(context: Context, foodId:Long):Food{
        return FoodDatabase.getInstance(context).getFoodDetails(foodId)
    }

    fun saveFood(context: Context, food: PersonalizedFood){
        FoodDatabase.getInstance(context).saveFood(food)
    }

    fun updateFood(context: Context, food: PersonalizedFood){
        val tempFood = FoodDatabase.getInstance(context).getFood(food.foodId, Date())
        if(tempFood != null)
            food.quantity += tempFood.quantity
        FoodDatabase.getInstance(context).saveFood(food)
    }

    fun setUpFoodDatabase(context:Context){

        val list = AddFoodPresenter().readFoodItemsFile(context)
        FoodDatabase.getInstance(context).addFoodList(list)
    }

    fun getTodaysFoodList(context: Context):List<DietFood>{
        return FoodDatabase.getInstance(context).getFood(Date())
    }

    fun getFoodListOn(date: Date, context: Context):List<DietFood>{
        return FoodDatabase.getInstance(context).getFood(date)
    }

    fun getFoodOn(date: Date, foodId:Long, context: Context):PersonalizedFood?{
        return FoodDatabase.getInstance(context).getFood(foodId, date)
    }

    fun deleteDietFood(food:PersonalizedFood, context: Context){
        FoodDatabase.getInstance(context).deleteDietFood(food)
    }

    fun getLastDateOfListing(context: Context):Date{
        val date = FoodDatabase.getInstance(context).getLastDateOfListing()
        return if(date == null) Date() else date
    }
}