package com.chandilsachin.diettracker.database

import android.arch.persistence.room.*
import android.content.Context

/**
 * Created by sachin on 22/5/17.
 */

@Database(entities = arrayOf(Food::class, PersonalizedFood::class), version = 1)
abstract class FoodDatabase : RoomDatabase(){
    abstract fun foodDao():FoodDao

    companion object{
        private val databaseName = "diet"

        var dbInstance:FoodDao? = null
        fun getInstance(context:Context):FoodDao?{
            if(dbInstance == null)
                dbInstance = Room.inMemoryDatabaseBuilder(context, FoodDatabase::class.java).build().foodDao()
            return dbInstance;
        }
    }
}
