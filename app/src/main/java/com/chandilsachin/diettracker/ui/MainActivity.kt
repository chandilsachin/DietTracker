package com.chandilsachin.diettracker.ui

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chandilsachin.diettracker.*
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.database.FoodDatabase
import com.chandilsachin.diettracker.mvp.view.ProgressDialog
import com.chandilsachin.diettracker.util.initViewModel
import kotlinx.android.synthetic.main.layout_diary_page.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setEvents()
        setUpDatabase()
        prepareDietList()
    }

    private fun setUpDatabase() {
        if (!InitPreferences(this).hasDataLoaded()) {
            setUpFoodDatabase()

        }
    }

    private fun prepareDietList() {
        val model = initViewModel(MainActivityModel::class.java)
        model.personalisedFoodList.observe(this, Observer{ list->
            list?.let {
                if(list.size > 0){
                    bindDataToList(list)
                }
            }

        })
    }

    private fun bindDataToList(list: List<Food>) {
        val adapter = DietListAdapter(baseContext, list)
        recyclerViewDietList.adapter = adapter
    }

    fun initViews() {
        recyclerViewDietList.layoutManager = LinearLayoutManager(this)
    }

    fun setEvents() {
        linearLayoutBreakfast.setOnClickListener {
            val intent = Intent(this@MainActivity, AddFoodActivity::class.java)
            startActivityForResult(intent, AddFoodActivity.CODE_FOOD_SELECTION)
        }
    }

    fun setUpFoodDatabase(){

        var dialog = ProgressDialog.show(this@MainActivity, "Preparing Database", "Preparing food database...", true)
        doAsync {
                val list = AddFoodPresenter().readFoodItemsFile(this@MainActivity)
                FoodDatabase.getInstance(baseContext).addFoodList(list)
                //DatabaseManager.getInstance(baseContext).addFoodList(list);

            uiThread {
                InitPreferences(this@MainActivity).setDataHasLoaded(true)
                dialog?.dismiss()
            }
        }
    }
}