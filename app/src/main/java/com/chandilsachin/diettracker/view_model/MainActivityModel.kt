package com.chandilsachin.diettracker.view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.chandilsachin.diettracker.database.DietFood
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.repository.FoodRepository
import java.util.concurrent.TimeUnit

/**
 * Created by Sachin Chandil on 02/05/2017.
 */

class MainActivityModel constructor(application: Application) : AndroidViewModel(application) {

    private val repo = FoodRepository()

    val personalisedFoodList: MutableLiveData<List<DietFood>> = MutableLiveData()

    /**
     * Fetches food list on given date date
     */
    fun fetchFoodListOn(date:Date):()->Unit{
        val list = repo.getFoodListOn(date, getApplication())
        return {
            personalisedFoodList.value = list
        }
    }

    fun deleteDietFood(food:PersonalizedFood){
        repo.deleteDietFood(food, getApplication())
    }

    /**
     * Sets up database for first time. This method should be called once in app's life type.
     * Check other.InitPreferences.hasDataLoaded() method.
     */
    fun prepareInitDatabase(){
        repo.setUpFoodDatabase(getApplication())
    }

    fun calculateDiaryPages():Int{
        val days = TimeUnit.DAYS.convert(Date().getTime() - repo.getLastDateOfListing(getApplication()).getTime(),
                TimeUnit.MILLISECONDS).toInt()
        return days + 1;
    }

}
