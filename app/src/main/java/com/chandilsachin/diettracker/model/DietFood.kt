package com.chandilsachin.diettracker.database

import android.arch.persistence.room.ColumnInfo

/**
 * Created by sachin on 22/5/17.
 */
class DietFood(@ColumnInfo(name = "food_name") var foodName: String = "",
               @ColumnInfo(name = "food_desc") var foodDesc: String = "",
               @ColumnInfo(name = "protein") var protein: Double = 0.0,
               @ColumnInfo(name = "carbs") var carbs: Double = 0.0,
               @ColumnInfo(name = "fat") var fat: Double = 0.0,
               @ColumnInfo(name = "calories") var calories: Double = 0.0,
               @ColumnInfo(name = "quantity") var quantity: Int = 0) {
    @ColumnInfo(name = "id")
    var id: Long = 0
}
