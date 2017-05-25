package com.chandilsachin.diettracker.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import java.util.*

/**
 * Created by Sachin Chandil on 02/05/2017.
 */

class MainActivityModel constructor(application: Application) : AndroidViewModel(application) {

    val personalisedFoodList: LiveData<List<Food>> = FoodDatabase.getInstance(getApplication()).getFood(Calendar.getInstance())
}
