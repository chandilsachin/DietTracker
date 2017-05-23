package com.chandilsachin.diettracker.database

import android.arch.persistence.room.TypeConverter
import java.util.*



/**
 * Created by sachin on 23/5/17.
 */
class Converter {

    companion object{
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @TypeConverter
        fun dateToTimestamp(date: Date): Long {
            return date.time
        }
    }
}