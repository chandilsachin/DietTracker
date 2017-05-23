package com.chandilsachin.diettracker.database

import android.arch.persistence.room.Room
import android.content.Context


object Database{
    private val databaseName = "diet"
    private var dbInstance:FoodDatabase? = null
    fun getInstance(context: Context):FoodDatabase?{
        if(dbInstance == null)
            dbInstance = Room.databaseBuilder(context, FoodDatabase::class.java, databaseName).build()
        return dbInstance
    }
}