package com.chandilsachin.diettracker.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import com.chandilsachin.diettracker.model.Date

@Entity(primaryKeys = arrayOf("food_id","date"),tableName = "personalised_food")
class PersonalizedFood(@ColumnInfo(name = "quantity") var quantity: Int = 1,
                       @ColumnInfo(name = "unit") var unit: String = "",
                       @ColumnInfo(name = "date") var date: Date = Date()){

    @ColumnInfo(name = "food_id")
    var foodId:Long = 0
}
