package com.chandilsachin.diettracker.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.repository.FoodRepository

/**
 * Created by sachin on 24/5/17.
 */
class AddFoodViewModel(application: Application):AndroidViewModel(application){
    private val repo = FoodRepository()
    var allFoodList:LiveData<List<Food>> = repo.getAllFodList(application)

}