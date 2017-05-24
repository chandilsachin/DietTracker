package com.chandilsachin.diettracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.chandilsachin.diettracker.database.PersonalizedFood
import kotlinx.android.synthetic.main.activity_food_details.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class FoodDetailsActivity : AppCompatActivity() {

    private var selectedFoodId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)
        
        selectedFoodId = getFoodIdFromIntent();
        fetchFoodDetails()
    }

    private fun getFoodIdFromIntent():Long{
        return intent.getLongExtra(AddFoodActivity.SELECTED_FOOD_ID, -1)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_food, menu)
        return true
    }

    private fun fetchFoodDetails():Unit{
        doAsync{
            //val food = FoodDatabase.getInstance(baseContext)?.getFoodDetails(selectedFoodId);
            val food = DatabaseManager.getInstance(baseContext).getFoodDetails(selectedFoodId);
            if(food != null)
                uiThread{
                    textViewFoodName.text = food.foodName
                    textViewFoodDesc.text = food.foodDesc
                    textViewCarbs.text = food.carbs.toString()
                    textViewFat.text = food.fat.toString()
                    textViewProtein.text = food.protein.toString()
                    textViewCalories.text = food.calories.toString()
                }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.addFood -> {
                doAsync {
                    //Database.getInstance(baseContext).
                    var food: PersonalizedFood = PersonalizedFood(editTextNoOfServings.text.toString().toInt(),"unit",Calendar.getInstance().time)
                    food.foodId = selectedFoodId
                    //FoodDatabase.getInstance(baseContext)?.saveFood(food)
                    DatabaseManager.getInstance(baseContext).saveFood(selectedFoodId, editTextNoOfServings.text.toString().toFloat(),Calendar.getInstance().time)
                    uiThread {
                        finish()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
