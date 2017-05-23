package com.chandilsachin.diettracker.database

import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by sachin on 22/5/17.
 */
interface FoodDao {

    companion object{
        const val ID = "id"
        const val NAME = "name"
        const val PROTEIN = "protein"
        const val DESC = "desc"
        const val CARBS = "carbs"
        const val FAT = "fat"

        const val ALL_FOOD_LIST = "all_food_list"
    }

    @Query("SELECT * FROM $ALL_FOOD_LIST WHERE $ID=(:id)")
    fun getFoodDetails(id:Int):Food

    @Insert
    fun addFoodList(list:ArrayList<Food>)
}