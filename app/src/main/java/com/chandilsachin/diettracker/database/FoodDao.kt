package com.chandilsachin.diettracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import java.util.*

/**
 * Created by sachin on 22/5/17.
 */
@Dao
interface FoodDao {

    companion object{
        const val ID = "id"
        const val NAME = "name"
        const val PROTEIN = "protein"
        const val DESC = "desc"
        const val CARBS = "carbs"
        const val FAT = "fat"

        const val DATE = "date"
        const val FOOD_ID = "food_id"

        const val ALL_FOOD_LIST = "all_food_list"
        const val PERSONALISED_FOOD_LIST = "personalised_food"
    }

    // food list operations

    /**
     * Returns food details of a food given by food_id
     */
    @Query("SELECT * FROM $ALL_FOOD_LIST WHERE $ID=:arg0")
    fun getFoodDetails(food_id:Long):Food

    @Query("SELECT * FROM $ALL_FOOD_LIST")
    fun getAllFoodList():LiveData<List<Food>>

    /**
     * Inserts food items in all_food_list
     */
    @Insert(onConflict = REPLACE)
    fun addFoodList(list:List<Food>)



    // Personalized food list operations

    @Insert(onConflict = REPLACE)
    fun saveFood(food:PersonalizedFood)

    @Query("SELECT * FROM $PERSONALISED_FOOD_LIST WHERE $FOOD_ID=:arg0 and $DATE=:arg1")
    fun getFood(foodId:Int, date:Calendar):PersonalizedFood

    //@Query("SELECT * FROM $ALL_FOOD_LIST where $ID in (select $FOOD_ID from $PERSONALISED_FOOD_LIST where $DATE = :arg0)")
    @Query("SELECT * FROM $ALL_FOOD_LIST where $ID in (select $FOOD_ID from $PERSONALISED_FOOD_LIST where $DATE = :arg0)")
    fun getFood(date:Calendar):LiveData<List<Food>>
}