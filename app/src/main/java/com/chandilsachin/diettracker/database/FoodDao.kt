package com.chandilsachin.diettracker.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.chandilsachin.diettracker.model.Date
import java.util.*

/**
 * Created by sachin on 22/5/17.
 */
@Dao
interface FoodDao {

    companion object{
        const val ID = "id"
        const val NAME = "food_name"
        const val PROTEIN = "protein"
        const val DESC = "food_desc"
        const val CARBS = "carbs"
        const val FAT = "fat"
        const val CALORIES = "calories"

        const val DATE = "date"
        const val FOOD_ID = "food_id"
        const val QUANTITY = "quantity"

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
    fun getFood(foodId:Int, date:Date):PersonalizedFood

    //@Query("SELECT * FROM $ALL_FOOD_LIST where $ID in (select $FOOD_ID from $PERSONALISED_FOOD_LIST where $DATE = :arg0)")
    @Query("SELECT $NAME,$DESC,$PROTEIN,$CARBS,$FAT,$CALORIES,$PERSONALISED_FOOD_LIST.$QUANTITY FROM $ALL_FOOD_LIST,$PERSONALISED_FOOD_LIST where $ID = $PERSONALISED_FOOD_LIST.$FOOD_ID and $DATE = :arg0")
    fun getFood(date:Date):List<DietFood>
}