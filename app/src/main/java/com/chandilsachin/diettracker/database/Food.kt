package com.chandilsachin.diettracker.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sachin on 22/5/17.
 */
@Entity(tableName = "all_food_list")
class Food (@ColumnInfo(name = "food_name") var foodName: String = "",
            @ColumnInfo(name = "food_desc") var foodDesc: String = "",
            @ColumnInfo(name = "protein") var protein: Double = 0.0,
            @ColumnInfo(name = "carbs") var carbs: Double = 0.0,
            @ColumnInfo(name = "fat") var fat: Double = 0.0,
            @ColumnInfo(name = "calories") var calories: Double = 0.0)
{
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @Ignore
    var quantity:Int = 0
}
