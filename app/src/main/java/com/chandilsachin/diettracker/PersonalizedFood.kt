package com.chandilsachin.diettracker

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import org.jetbrains.annotations.PropertyKey
import java.util.*

class PersonalizedFood(var quantity: Int,
                       var unit: String,
                       var date: Date){

    var foodId:Long = 0
}
