package com.chandilsachin.diettracker

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import android.widget.Toast
import com.chandilsachin.diettracker.database.Food
import com.chandilsachin.diettracker.mvp.view.ProgressDialog
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dbManager: DatabaseManager? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbManager = DatabaseManager.getInstance(this)
        presenter = MainPresenter()
        initViews()
        setEvents()
        setUpDatabase()
        prepareDietList()
    }

    private fun setUpDatabase() {
        if (!InitPreferences(this).hasDataLoaded()) {
            SetUpFoodDatabase().execute()
        }
    }

    private fun prepareDietList() {
        Thread(Runnable {
            val list = presenter!!.getDietList(this@MainActivity)
            this@MainActivity.runOnUiThread { bindDataToList(list) }
        }).start()

    }


    private fun bindDataToList(list: ArrayList<Food>) {
        val adapter = DietListAdapter(this, list)
        recyclerViewDietList!!.adapter = adapter
    }

    private var relativeLayoutAddBreakfast: RelativeLayout? = null
    private var linearLayoutBreakfast: CardView? = null
    private var recyclerViewDietList: RecyclerView? = null
    fun initViews() {
        linearLayoutBreakfast = findViewById(R.id.linearLayoutBreakfast) as CardView
        relativeLayoutAddBreakfast = linearLayoutBreakfast!!.findViewById(R.id.relativeLayoutAddFood) as RelativeLayout
        recyclerViewDietList = findViewById(R.id.recyclerViewDietList) as RecyclerView
        recyclerViewDietList!!.layoutManager = LinearLayoutManager(this)
    }

    fun setEvents() {
        relativeLayoutAddBreakfast!!.setOnClickListener {
            val intent = Intent(this@MainActivity, AddFoodActivity::class.java)
            startActivityForResult(intent, AddFoodActivity.CODE_FOOD_SELECTION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            AddFoodActivity.CODE_FOOD_SELECTION -> {
                val selectedFood = data.getIntExtra(AddFoodActivity.SELECTED_FOOD_ID, -1)
                dbManager!!.saveFood(selectedFood, 1f, Calendar.getInstance().time)
                Toast.makeText(this, "Selected Item: " + selectedFood, Toast.LENGTH_SHORT).show()
                prepareDietList()
            }
        }
    }

    override fun onStart() {
        super.onStart()

    }

    internal inner class SetUpFoodDatabase : AsyncTask<Void, Int, Void>() {

        var dialog: android.app.ProgressDialog? = null

        override fun onPreExecute() {
            super.onPreExecute()
            dialog = ProgressDialog.show(this@MainActivity, "Preparing Database", "Preparing food database...", true)
        }

        override fun onPostExecute(aVoid: Void) {
            super.onPostExecute(aVoid)
            dialog?.dismiss()
            InitPreferences(this@MainActivity).setDataHasLoaded(true)
        }

        override fun doInBackground(vararg params: Void): Void? {
            try {
                val list = AddFoodPresenter().readFoodItemsFile(this@MainActivity)
                dbManager!!.addFoodList(list)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }
    }
}