package com.chandilsachin.diettracker

import android.content.Context
import android.content.res.AssetManager
import android.util.JsonReader

import com.chandilsachin.diettracker.database.FoodDatabase
import com.chandilsachin.diettracker.io.JSONReader
import com.ne1c.rainbowmvp.ViewState
import com.ne1c.rainbowmvp.ViewStateListener
import com.ne1c.rainbowmvp.base.BasePresenter

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.io.InputStream
import java.util.ArrayList

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class AddFoodPresenter {

    @Throws(IOException::class)
    fun readFoodItemsFile(context: Context): ArrayList<com.chandilsachin.diettracker.database.Food> {

        val list = ArrayList<com.chandilsachin.diettracker.database.Food>()
        try {
            val jsonArray: JSONArray
            jsonArray = JSONReader.readJsonFileToArrayFromAssets(context.assets, "food_Items.json")
            for (i in 0..jsonArray.length() - 1) {
                val `object` = jsonArray.getJSONObject(i)
                val fact = `object`.getJSONObject("facts")
                val food = com.chandilsachin.diettracker.database.Food(`object`.getString("food_name"), `object`.getString("food_desc"), fact.getDouble("protein"),
                        fact.getDouble("carbs"), fact.getDouble("fat"), fact.getDouble("calorie"))
                list.add(food)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    companion object {
        val TAG = AddFoodPresenter::class.java.name
    }
}
