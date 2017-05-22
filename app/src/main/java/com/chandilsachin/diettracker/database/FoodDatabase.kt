package com.chandilsachin.diettracker.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by sachin on 22/5/17.
 */

object Database{
    private val databaseName = "diet"
    private var dbInstance:FoodDatabase? = null
    fun getInstance(context: Context):FoodDatabase?{
        if(dbInstance == null)
            dbInstance = Room.databaseBuilder(context, FoodDatabase::class.java, databaseName).build()
        return dbInstance
    }
}

@Database(entities = arrayOf(Food::class), version = 1)
abstract class FoodDatabase : RoomDatabase(){
    abstract fun FoodDao()
}
