package com.chandilsachin.diettracker.database
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

/**
 * Created by sachin on 22/5/17.
 */

@Database(entities = arrayOf(Food::class, PersonalizedFood::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FoodDatabase : RoomDatabase(){
    abstract fun foodDao():FoodDao

    companion object{
        var TEST_MODE = false
        private val databaseName = "diet"

        var dbInstance:FoodDao? = null
        fun getInstance(context:Context):FoodDao{
            if(dbInstance == null){
                if(TEST_MODE)
                    dbInstance = Room.inMemoryDatabaseBuilder(context, FoodDatabase::class.java).allowMainThreadQueries().build().foodDao()
                else
                    dbInstance = Room.databaseBuilder(context, FoodDatabase::class.java, databaseName).build().foodDao()

            }

            return dbInstance!!;
        }
    }
}
