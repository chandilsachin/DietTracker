package com.chandilsachin.diettracker.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sachin on 22/5/17.
 */
@Entity
class Food(@ColumnInfo(name = "food_name") var foodName: String,
           @ColumnInfo(name = "food_desc") var foodDesc: String,
           @ColumnInfo(name = "protein") var protein: Double,
           @ColumnInfo(name = "carbs") var carbs: Double,
           @ColumnInfo(name = "fat") var fat: Double)
{
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "calories")
    var calories: Double = 0.toDouble()
}
