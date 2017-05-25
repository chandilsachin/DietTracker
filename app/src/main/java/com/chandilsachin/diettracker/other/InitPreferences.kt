package com.chandilsachin.diettracker.other

import android.content.Context

import com.chandilsachin.diettracker.io.TemporaryDataManager

/**
 * Created by Sachin Chandil on 01/05/2017.
 */

class InitPreferences(context: Context) {

    internal var db: TemporaryDataManager

    init {
        db = TemporaryDataManager(context, PREFERENCE_NAME)
    }

    fun setDataHasLoaded(value: Boolean) {
        db.setBoolean(DATA_HAS_LOADED, value)
        db.commit()
    }

    fun hasDataLoaded(): Boolean {
        return db.getBoolean(DATA_HAS_LOADED)
    }

    companion object {
        private val PREFERENCE_NAME = "INIT_PREFERENCE"

        private val DATA_HAS_LOADED = "DATA_HAS_LOADED"
    }
}
