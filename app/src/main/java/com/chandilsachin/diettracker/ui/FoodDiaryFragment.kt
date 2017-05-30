package com.chandilsachin.diettracker.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.adapters.DietListAdapter
import com.chandilsachin.diettracker.database.DietFood
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.other.InitPreferences
import com.chandilsachin.diettracker.util.BaseFragment
import com.chandilsachin.diettracker.util.annotation.RequiresTagName
import com.chandilsachin.diettracker.util.initViewModel
import com.chandilsachin.diettracker.util.loadFragment
import com.chandilsachin.diettracker.util.setSupportActionBar
import com.chandilsachin.diettracker.view_model.MainActivityModel
import kotlinx.android.synthetic.main.fragment_food_diary.*
import kotlinx.android.synthetic.main.layout_meal_listing.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates

@RequiresTagName("FoodDiaryFragment")
class FoodDiaryFragment : BaseFragment() {

    var date: Date by Delegates.notNull()

    val model: MainActivityModel by lazy {
        initViewModel(MainActivityModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_food_diary, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        init()
        setEvents()
        setUpDatabase()
        prepareDietList()
        super.onViewCreated(view, savedInstanceState)
    }

    fun init() {

        setSupportActionBar(my_toolbar)
        recyclerViewDietList.layoutManager = LinearLayoutManager(context)
        date = arguments.getSerializable(DATE) as Date
    }

    private fun setUpDatabase() {

        //val dialog = ProgressDialog.show(baseContext, "Preparing Database", "Preparing food database...", true)
        doAsync {
            if (!InitPreferences(context).hasDataLoaded()) {
                model.prepareInitDatabase()
            }
            uiThread {
                //dialog?.dismiss()
            }
        }

    }

    private fun prepareDietList() {
        val adapter = DietListAdapter(context, emptyList())
        recyclerViewDietList.adapter = adapter
        model.personalisedFoodList.observe(this, Observer { list ->
            list?.let {
                if (list.isNotEmpty()) {
                    adapter.foodList = list
                    adapter.notifyDataSetChanged()
                    setFact(list)
                }
            }
        })
        doAsync {
            val uiTask = model.fetchFoodListOn(date)
            uiThread {
                uiTask()
            }
        }

    }

    private fun setFact(list: List<DietFood>) {
        var calorie: Double = 0.0
        var protein: Double = 0.0
        var carbs: Double = 0.0
        var fat: Double = 0.0
        for (item in list) {
            calorie += item.calories
            protein += item.protein
            carbs += item.carbs
            fat += item.fat
        }
        textViewTotalCalories.text = calorie.toString() + "g"
        textViewTotalProtein.text = protein.toString() + "g"
        textViewTotalCarbs.text = carbs.toString() + "g"
        textViewTotalFat.text = fat.toString() + "g"
    }

    fun setEvents() {
        linearLayoutBreakfast.setOnClickListener {
            loadFragment(R.id.frameLayoutFragment, FoodListFragment.getInstance(activity))
        }
    }

    companion object {
        private val DATE = "foodDiaryFragment.date"
        fun getInstance(date: Date = Date(), activity: FragmentActivity): Fragment {
            var fragment = findInstance(activity)
            if (fragment == null) {
                fragment = FoodDiaryFragment()
            }
            val bundle = Bundle()
            bundle.putSerializable(DATE, date)
            fragment.arguments = bundle
            return fragment
        }
    }

}