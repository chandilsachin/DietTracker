package com.chandilsachin.diettracker.ui

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.chandilsachin.diettracker.adapters.FoodListAdapter
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.model.Date
import com.chandilsachin.diettracker.util.BaseFragment

import com.chandilsachin.diettracker.util.initViewModel
import com.chandilsachin.diettracker.util.loadFragment
import com.chandilsachin.diettracker.view_model.AddFoodViewModel
import kotlinx.android.synthetic.main.fragment_add_food.*

class FoodListFragment : BaseFragment(){

    val model: AddFoodViewModel by lazy {
        initViewModel(AddFoodViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        recyclerViewFoodList.layoutManager = LinearLayoutManager(context)
        prepareFoodItemList()
        super.onViewCreated(view, savedInstanceState)
    }

    fun prepareFoodItemList() {
        model.allFoodList.observe(this, Observer { list ->
            list.let {
                populateListItems(list!!)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_add_food, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    fun populateListItems(list: List<com.chandilsachin.diettracker.database.Food>) {
        val adapter = FoodListAdapter(context, list){ foodId ->
            loadFragment(R.id.frameLayoutFragment, FoodDetailsFragment.getInstance(foodId, activity as AppCompatActivity?))
        }
        recyclerViewFoodList.adapter = adapter
    }

    companion object{

        val SELECTED_FOOD_ID = "selectedIndex"
        val CODE_FOOD_SELECTION = 1

        fun getInstance(activity: FragmentActivity? = null): Fragment {
            var fragment = findInstance(activity!!)
            if(fragment == null){
                fragment = FoodListFragment()
            }
            return fragment
        }
    }
}