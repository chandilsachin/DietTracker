package com.chandilsachin.diettracker.database

import android.arch.persistence.room.TypeConverter
import com.chandilsachin.diettracker.model.Date
import java.util.*


/**
 * Created by sachin on 24/5/17.
 */
class Converters{

        @TypeConverter
        fun fromTimestamp(value: String): Date {
            val arr = value.split("-")
            val cal = Date(arr[0].toInt(), arr[1].toInt(), arr[2].toInt())
            return cal
        }

        @TypeConverter
        fun dateToTimestamp(date: Date): String {
            return "${date.date}-${date.month}-${date.year}"
        }

}