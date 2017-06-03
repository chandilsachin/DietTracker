package com.chandilsachin.diettracker.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.repository.FoodDetailsRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by sachin on 27/5/17.
 */
class FoodDetailsViewModel(application: Application):AndroidViewModel(application){
    private val repo = FoodDetailsRepository()
    val foodDetails = MutableLiveData<Food>()

    fun fetchFoodDetails(foodId:Long){
        doAsync {
            var list = repo.getFoodDetails(getApplication(), foodId)
            uiThread {
                foodDetails.value = list
            }
        }


    }

    fun saveFoodDetails(food:PersonalizedFood){
        repo.saveFood(getApplication(), food)
    }

    fun updateFoodDetails(food:PersonalizedFood){
        repo.updateFood(getApplication(), food)
    }
}