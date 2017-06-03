package com.chandilsachin.diettracker.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.database.PersonalizedFood
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.util.*
import com.chandilsachin.diettracker.util.annotation.RequiresTagName
import com.chandilsachin.diettracker.view_model.FoodDetailsViewModel
import kotlinx.android.synthetic.main.fragment_food_details.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

@RequiresTagName("FoodDetailsFragment")
class FoodDetailsFragment : BaseFragment() {

    val model: FoodDetailsViewModel by lazy {
        initViewModel(FoodDetailsViewModel::class.java)
    }

    private var selectedFoodId: Long = -1

    private var modeReplace = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_food_details, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun init() {
        getArgumentValues()

        setSupportActionBar(my_toolbar)
        setDisplayHomeAsUpEnabled(true)
        activity.setTitle(if (modeReplace) R.string.updateFoodDetails else R.string.foodDetailsPage)
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
        if(modeReplace){
            doAsync {
                val quantity = model.getFoodQuantityOn(selectedFoodId, Date())
                uiThread {
                    editTextNoOfServings.setText(quantity.toString())
                }
            }

        }

        fetchFoodDetails()
    }


    private fun getArgumentValues() {
        selectedFoodId = arguments.getLong(FoodListFragment.SELECTED_FOOD_ID, -1)
        modeReplace = arguments.getBoolean(MODE_REPLACE)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (modeReplace)
            inflater?.inflate(R.menu.menu_update_food, menu)
        else
            inflater?.inflate(R.menu.menu_add_food, menu)
    }

    private fun fetchFoodDetails(): Unit {
        model.fetchFoodDetails(selectedFoodId)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val food: PersonalizedFood = PersonalizedFood(editTextNoOfServings.text.toString().toInt(), "unit")
        food.foodId = selectedFoodId
        when (item?.itemId) {
            R.id.addFood -> {
                doAsync {
                    model.updateFoodDetails(food)
                    getAppCompactActivity().supportFragmentManager.popBackStack(null,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    // Switch fragment to FoodDiaryFragment
                    loadFragment(R.id.frameLayoutFragment, PersonalizedFoodFragment.getInstance(activity = activity))
                }
            }
            R.id.updateFood ->{
                doAsync {
                    model.saveFoodDetails(food)
                    // Switch fragment to FoodDiaryFragment
                    loadFragment(R.id.frameLayoutFragment, PersonalizedFoodFragment.getInstance(activity = activity))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private val MODE_REPLACE = "modeReplace"

        fun getInstance(foodId: Long, replace: Boolean = false, activity: AppCompatActivity? = null): Fragment {
            var fragment = BaseFragment.findInstance(activity!!)
            if (fragment == null) {
                fragment = FoodDetailsFragment()
            }
            val bundle = Bundle()
            bundle.putLong(FoodListFragment.SELECTED_FOOD_ID, foodId)
            bundle.putBoolean(MODE_REPLACE, replace)
            fragment.arguments = bundle
            return fragment
        }
    }
}
