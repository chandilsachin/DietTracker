package com.chandilsachin.diettracker

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import com.chandilsachin.diettracker.Food
import com.chandilsachin.diettracker.util.initViewModel
import com.chandilsachin.diettracker.view_model.AddFoodViewModel
import kotlinx.android.synthetic.main.activity_add_food.*

import java.io.IOException
import java.util.ArrayList

class AddFoodActivity : LifecycleActivity() {


    override fun onStart() {
        super.onStart()
        //getPresenter().bindView(this);
    }

    override fun onStop() {
        super.onStop()
        //getPresenter().unbindView();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)
        recyclerViewFoodList.layoutManager = LinearLayoutManager(this)
        prepareFoodItemList()

    }

    fun prepareFoodItemList() {
        val model = initViewModel(AddFoodViewModel::class.java)
        model.allFoodList.observe(this, Observer { list ->
            list.let {
                populateListItems(list!!)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_add_food, menu);
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    fun populateListItems(list: List<com.chandilsachin.diettracker.database.Food>) {
        val adapter = FoodListAdapter(this, list){ foodId ->
            val intent = Intent(this@AddFoodActivity, FoodDetailsActivity::class.java)
            intent.putExtra(SELECTED_FOOD_ID, foodId)
            //setResult(CODE_FOOD_SELECTION, intent);
            startActivity(intent)
        }
        recyclerViewFoodList.adapter = adapter
    }

    companion object {

        val SELECTED_FOOD_ID = "selectedIndex"
        val CODE_FOOD_SELECTION = 1
    }
}