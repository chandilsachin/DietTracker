package com.chandilsachin.diettracker

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_food_details.*
import java.util.*

class FoodDetailsActivity : AppCompatActivity() {


    private var selectedFoodId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)
        
        selectedFoodId = getFoodIdFromIntent();
        fetchFoodDetails()
    }

    private fun getFoodIdFromIntent():Int{
        return intent.getIntExtra(AddFoodActivity.SELECTED_FOOD_ID, -1)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_food, menu)
        return true
    }

    private fun fetchFoodDetails():Unit{
        val food = DatabaseManager.getInstance(baseContext).getFoodDetails(selectedFoodId);
        textViewFoodName.text = food.foodName
        textViewFoodDesc.text = food.foodDesc
        textViewCarbs.text = food.carbs.toString()
        textViewFat.text = food.fat.toString()
        textViewProtein.text = food.protein.toString()
        textViewCalories.text = food.calories.toString()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.addFood -> {
                DatabaseManager.getInstance(baseContext).saveFood(selectedFoodId, Integer.parseInt(editTextNoOfServings.text.toString()).toFloat(),Calendar.getInstance().time)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
