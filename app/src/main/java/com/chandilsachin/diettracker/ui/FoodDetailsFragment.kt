package com.chandilsachin.diettracker.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.util.BaseFragment
import com.chandilsachin.diettracker.util.annotation.RequiresTagName
import com.chandilsachin.diettracker.util.initViewModel
import com.chandilsachin.diettracker.util.loadFragment
import com.chandilsachin.diettracker.util.setSupportActionBar
import com.chandilsachin.diettracker.view_model.FoodDetailsViewModel
import kotlinx.android.synthetic.main.fragment_food_details.*
import org.jetbrains.anko.doAsync

@RequiresTagName("FoodDetailsFragment")
class FoodDetailsFragment : BaseFragment() {

    /*private val mRegistry = LifecycleRegistry(this)
    override fun getLifecycle(): LifecycleRegistry {
        return mRegistry
    }*/

    val model: FoodDetailsViewModel by lazy {
        initViewModel(FoodDetailsViewModel::class.java)
    }

    private var selectedFoodId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_food_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        init()
        selectedFoodId = getFoodIdFromIntent()
        fetchFoodDetails()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun init() {
        setSupportActionBar(my_toolbar)
        activity.setTitle(R.string.foodDetailsPage)
        model.foodDetails.observe(this, Observer { food ->
            food?.let {
                textViewFoodName.text = food.foodName
                textViewFoodDesc.text = food.foodDesc
                textViewCarbs.text = food.carbs.toString()
                textViewFat.text = food.fat.toString()
                textViewProtein.text = food.protein.toString()
                textViewCalories.text = food.calories.toString()
            }
        })
    }


    private fun getFoodIdFromIntent(): Long {
        return arguments.getLong(FoodListFragment.SELECTED_FOOD_ID, -1)
    }


    /*override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater) {


    }*/

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_add_food, menu)
    }

    private fun fetchFoodDetails(): Unit {
        model.fetchFoodDetails(selectedFoodId)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addFood -> {
                doAsync {
                    val food: PersonalizedFood = PersonalizedFood(editTextNoOfServings.text.toString().toInt(), "unit")
                    food.foodId = selectedFoodId
                    model.saveFoodDetails(food)
                    // Switch fragment to FoodDiaryFragment
                    loadFragment(R.id.frameLayoutFragment, FoodDiaryFragment.getInstance(activity = activity))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {


        fun getInstance(foodId: Long, activity: AppCompatActivity? = null): Fragment {
            var fragment = BaseFragment.findInstance(activity!!)
            if (fragment == null) {
                fragment = FoodDetailsFragment()
            }
            val bundle = Bundle()
            bundle.putLong(FoodListFragment.SELECTED_FOOD_ID, foodId)
            fragment.arguments = bundle
            return fragment
        }
    }
}
