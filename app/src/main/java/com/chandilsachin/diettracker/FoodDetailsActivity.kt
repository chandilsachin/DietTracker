package com.chandilsachin.diettracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_food_details.*
import java.util.*

class FoodDetailsActivity : AppCompatActivity() {


    private var selectedFoodIndex : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)
        
        selectedFoodIndex = getFoodIdFromIntent();
    }

    private fun getFoodIdFromIntent():Int{
        return intent.getIntExtra("selectedIndex", -1)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_food, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.addFood -> {
                DatabaseManager.getInstance(baseContext).saveFood(selectedFoodIndex, Integer.parseInt(editTextNoOfServings.text.toString()).toFloat(),Calendar.getInstance().time)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
