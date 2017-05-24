package com.chandilsachin.diettracker

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sachin on 22/5/17.
 */
class Food( var foodName: String,
           var foodDesc: String,
           var protein: Double,
           var carbs: Double,
           var fat: Double)
{
    var id: Long = 0
    var calories: Double = 0.toDouble()
}
