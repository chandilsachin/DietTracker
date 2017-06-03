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
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.other.InitPreferences
import com.chandilsachin.diettracker.util.*
import com.chandilsachin.diettracker.util.annotation.RequiresTagName
import com.chandilsachin.diettracker.view_model.MainActivityModel
import kotlinx.android.synthetic.main.fragment_food_diary.*
import kotlinx.android.synthetic.main.layout_meal_listing.*
import kotlinx.android.synthetic.main.toolbar_layout.*
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
        recyclerViewDietList.layoutManager = LinearLayoutManager(context)
        date = arguments.getSerializable(DATE) as Date
        textViewDate.text = date.getPrettyDate()

        if(!date.equals(Date()))
            relativeLayoutAddFood.visibility = View.GONE
    }

    private fun setUpDatabase() {

        doAsync {
            if (!InitPreferences(context).hasDataLoaded()) {
                model.prepareInitDatabase()
                InitPreferences(context).setDataHasLoaded(true)
            }
        }
    }

    private fun prepareDietList() {
        val adapter = DietListAdapter(context, date.equals(Date()))
        recyclerViewDietList.adapter = adapter
        model.personalisedFoodList.observe(this, Observer { list ->
            list?.let {
                adapter.foodList = list
                setFact(list)
                adapter.notifyDataSetChanged()
            }
        })

        fetchFoodList()

        adapter.onItemDeleteClick = { food ->
            val foodObj = PersonalizedFood()
            foodObj.foodId = food.id
            foodObj.date = date
            doAsync {
                model.deleteDietFood(foodObj)
                uiThread {
                    fetchFoodList()
                }
            }
        }

        adapter.onItemEditClick = { food ->
            loadFragment(R.id.frameLayoutFragment, FoodDetailsFragment.getInstance(food.id,
                    true, getAppCompactActivity()))
        }

    }

    private fun fetchFoodList() {
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
            calorie += item.calories * item.quantity
            protein += item.protein * item.quantity
            carbs += item.carbs * item.quantity
            fat += item.fat * item.quantity
        }
        textViewTotalCalories.text = calorie.toDecimal(2)
        textViewTotalProtein.text = protein.toDecimal(2) + "g"
        textViewTotalCarbs.text = carbs.toDecimal(2) + "g"
        textViewTotalFat.text = fat.toDecimal(2) + "g"
    }

    fun setEvents() {
        relativeLayoutAddFood.setOnClickListener {
            loadFragment(R.id.frameLayoutFragment, FoodListFragment.getInstance(activity))
        }
    }

    companion object {
        private val DATE = "foodDiaryFragment.date"
        fun getInstance(date: Date = Date(), activity: FragmentActivity? = null): Fragment {
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