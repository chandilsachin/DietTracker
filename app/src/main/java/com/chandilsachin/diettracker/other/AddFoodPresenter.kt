package com.chandilsachin.diettracker.other

import android.os.Bundle
import com.ne1c.rainbowmvp.base.BaseActivity

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class AddFoodPresenter {

    @Throws(java.io.IOException::class)
    fun readFoodItemsFile(context: android.content.Context): java.util.ArrayList<com.chandilsachin.diettracker.database.Food> {

        val list = java.util.ArrayList<com.chandilsachin.diettracker.database.Food>()
        try {
            val jsonArray: org.json.JSONArray
            jsonArray = com.chandilsachin.diettracker.io.JSONReader.readJsonFileToArrayFromAssets(context.assets, "food_Items.json")
            for (i in 0..jsonArray.length() - 1) {
                val `object` = jsonArray.getJSONObject(i)
                val fact = `object`.getJSONObject("facts")
                val food = com.chandilsachin.diettracker.database.Food(`object`.getString("food_name"), `object`.getString("food_desc"), fact.getDouble("protein"),
                        fact.getDouble("carbs"), fact.getDouble("fat"), fact.getDouble("calories"))
                list.add(food)
            }
        } catch (e: org.json.JSONException) {
            e.printStackTrace()
        }

        return list
    }

    companion object {
        val TAG = com.chandilsachin.diettracker.other.AddFoodPresenter::class.java.name
    }
}
