package com.chandilsachin.diettracker.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import java.util.*
import kotlin.collections.ArrayList

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

        const val ALL_FOOD_LIST = "food"
        const val PERSONALISED_FOOD_LIST = "personalised_food"
    }

    // food list operations

    /**
     * Returns food details of a food given by food_id
     */
    @Query("SELECT * FROM $ALL_FOOD_LIST WHERE $ID=:food_id")
    fun getFoodDetails(food_id:Long):Food

    /**
     * Inserts food items in all_food_list
     */
    @Insert
    fun addFoodList(list:ArrayList<Food>)



    // Personalized food list operations

    @Insert(onConflict = REPLACE)
    fun saveFood(food:PersonalizedFood)

    @Query("SELECT * FROM $PERSONALISED_FOOD_LIST WHERE $FOOD_ID=:foodId and $DATE=:date")
    fun getFood(foodId:Int, data:Long):PersonalizedFood

    @Query("SELECT * FROM $ALL_FOOD_LIST where $ID in (select $FOOD_ID from $PERSONALISED_FOOD_LIST where $DATE = :date)")
    fun getFood(date:Long):ArrayList<Food>
}