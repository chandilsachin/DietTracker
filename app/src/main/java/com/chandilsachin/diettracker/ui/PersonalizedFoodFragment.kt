package com.chandilsachin.diettracker.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.adapters.FoodDiaryAdapter
import com.chandilsachin.diettracker.util.BaseFragment
import com.chandilsachin.diettracker.util.getAppCompactActivity
import com.chandilsachin.diettracker.util.initViewModel
import com.chandilsachin.diettracker.util.setSupportActionBar
import com.chandilsachin.diettracker.view_model.MainActivityModel
import kotlinx.android.synthetic.main.fragment_personalized_food.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class PersonalizedFoodFragment : BaseFragment() {

    val model: MainActivityModel by lazy {
        initViewModel(MainActivityModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_personalized_food, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    private var adapter: FoodDiaryAdapter? = null
    var days:Int = 0;
    private fun init() {
        setSupportActionBar(my_toolbar)
        getAppCompactActivity().supportActionBar?.setTitle(R.string.dietDiary)
        doAsync {
            days = model.calculateDiaryPages()
            uiThread {
                adapter = FoodDiaryAdapter(activity, childFragmentManager, days)
                viewPagerPersonalisedFood.adapter = adapter
                viewPagerPersonalisedFood.post {
                    viewPagerPersonalisedFood.setCurrentItem(days, false)
                    adapter?.notifyDataSetChanged()
                }
            }
        }

        viewPagerPersonalisedFood.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageSelected(p0: Int) {
                if(p0 == 0)
                    Snackbar.make(viewPagerPersonalisedFood,
                            "You have reached to last page.", Snackbar.LENGTH_SHORT).show()

            }
        })
    }

    companion object {

        fun getInstance(activity: FragmentActivity): Fragment {
            var fragment = findInstance(activity)
            if (fragment == null) {
                fragment = PersonalizedFoodFragment()
            }
            return fragment
        }
    }
}