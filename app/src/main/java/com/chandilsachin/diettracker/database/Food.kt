package com.chandilsachin.diettracker.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by sachin on 22/5/17.
 */
@Entity
class Food(var foodName: String, var foodDesc: String, var protein: Double, var carbs: Double, var fat: Double)
{
    @PrimaryKey(autoGenerate = true)
    var foodId: Int = 0
    var calories: Double = 0.toDouble()
}
