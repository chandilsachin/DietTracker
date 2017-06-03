package com.chandilsachin.diettracker.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.adapters.FoodListAdapter
import com.chandilsachin.diettracker.util.*
import com.chandilsachin.diettracker.util.annotation.RequiresTagName
import com.chandilsachin.diettracker.view_model.AddFoodViewModel
import kotlinx.android.synthetic.main.fragment_add_food.*
import kotlinx.android.synthetic.main.toolbar_layout.*

@RequiresTagName("FoodListFragment")
class FoodListFragment : BaseFragment() {

    val model: AddFoodViewModel by lazy {
        initViewModel(AddFoodViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        init()
        prepareFoodItemList()
        super.onViewCreated(view, savedInstanceState)
    }

    fun init(){
        setSupportActionBar(my_toolbar)
        setDisplayHomeAsUpEnabled(true)
        activity.setTitle(R.string.addFood)
        recyclerViewFoodList.layoutManager = LinearLayoutManager(context)
    }
    var adapter:FoodListAdapter? = null
    fun prepareFoodItemList() {
        adapter = FoodListAdapter(context) { foodId ->
            loadFragment(R.id.frameLayoutFragment, FoodDetailsFragment.getInstance(foodId, false, activity as AppCompatActivity?))
        }
        recyclerViewFoodList.adapter = adapter
        model.allFoodList.observe(this, Observer { list ->
            list?.let {
                if(list.isNotEmpty())
                {
                    adapter?.foodList = list
                    adapter?.filter?.filter("")
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.food_list_menu, menu)
        val searchView = menu?.findItem(R.id.menu_idSearch)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                adapter!!.filter.filter(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter!!.filter.filter(p0)
                return true
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    companion object {

        val SELECTED_FOOD_ID = "selectedIndex"
        val CODE_FOOD_SELECTION = 1

        fun getInstance(activity: FragmentActivity? = null): Fragment {
            var fragment = findInstance(activity!!)
            if (fragment == null) {
                fragment = FoodListFragment()
            }
            return fragment
        }
    }
}