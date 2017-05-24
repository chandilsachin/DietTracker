package com.chandilsachin.diettracker

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import com.chandilsachin.diettracker.Food
import com.chandilsachin.diettracker.mvp.view.ProgressDialog
import kotlinx.android.synthetic.main.layout_diary_page.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.util.*

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
        val model = ViewModelProviders.of(this).get(MainActivityModel::class.java)
        model.userLiveData.observe(this, android.arch.lifecycle.Observer<ArrayList<Food>> { list->
            list?.let {
                if(list.size > 0){
                    bindDataToList(list)
                }
            }

        })
        /*doAsync {
            //val list = FoodDatabase.getInstance(baseContext)?.getFood(Calendar.getInstance().time.time)
            val list = DatabaseManager.getInstance(baseContext).getFoodOnDate(Calendar.getInstance().time);
            uiThread {
                if (list != null)
                    bindDataToList(list)
            }
        }*/
    }


    private fun bindDataToList(list: ArrayList<Food>) {
        val adapter = DietListAdapter(baseContext, list)
        recyclerViewDietList.adapter = adapter
    }

    private var relativeLayoutAddBreakfast: RelativeLayout? = null
    private var linearLayoutBreakfast: CardView? = null
    fun initViews() {
        linearLayoutBreakfast = findViewById(R.id.linearLayoutBreakfast) as CardView
        relativeLayoutAddBreakfast = linearLayoutBreakfast!!.findViewById(R.id.relativeLayoutAddFood) as RelativeLayout
        recyclerViewDietList.layoutManager = LinearLayoutManager(this)
    }

    fun setEvents() {
        relativeLayoutAddBreakfast!!.setOnClickListener {
            val intent = Intent(this@MainActivity, AddFoodActivity::class.java)
            startActivityForResult(intent, AddFoodActivity.CODE_FOOD_SELECTION)
        }
    }

    fun setUpFoodDatabase(){

        var dialog = ProgressDialog.show(this@MainActivity, "Preparing Database", "Preparing food database...", true)
        doAsync {
            try {
                val list = AddFoodPresenter().readFoodItemsFile(this@MainActivity)
                //FoodDatabase.getInstance(baseContext)?.addFoodList(list)
                DatabaseManager.getInstance(baseContext).addFoodList(list);
            } catch (e: IOException) {
                e.printStackTrace()
            }
            uiThread {
                InitPreferences(this@MainActivity).setDataHasLoaded(true)
                dialog?.dismiss()
            }
        }
    }
}