package com.chandilsachin.diettracker.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import org.jetbrains.annotations.PropertyKey
import java.util.*

//@Entity(primaryKeys = arrayOf("food_id","date"))
class PersonalizedFood(@ColumnInfo(name = "quantity") var quantity: Int,
                       @ColumnInfo(name = "unit") var unit: String,
                       @ColumnInfo(name = "date") var date: Date){

    @ColumnInfo(name = "food_id")
    var foodId:Long = 0
}
