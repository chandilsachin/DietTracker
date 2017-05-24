package com.chandilsachin.diettracker.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import org.jetbrains.anko.doAsync

/**
 * Created by sachin on 24/5/17.
 */
class AddFoodViewModel(application: Application):AndroidViewModel(application){
    var allFoodList:LiveData<List<Food>> = FoodDatabase.getInstance(getApplication()).getAllFoodList()

}