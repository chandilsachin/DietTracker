package com.chandilsachin.diettracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.database.DietFood
import com.chandilsachin.diettracker.database.Food

import java.util.ArrayList

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class FoodListAdapter(context: Context, var onItemClick:(Long)-> Unit) :
        RecyclerView.Adapter<FoodListAdapter.ViewHolder>(), Filterable {

    var foodList: List<Food> = emptyList()
    private var displayFoodList: ArrayList<Food> = arrayListOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(displayFoodList[position])

    override fun getItemCount(): Int {
        return displayFoodList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewFoodName = itemView.findViewById(R.id.textViewFoodName) as TextView
        var textViewFoodDesc = itemView.findViewById(R.id.textViewFoodDesc) as TextView

        fun bind(item:Food){
            textViewFoodName.text = item.foodName
            textViewFoodDesc.text = item.foodDesc

            itemView.setOnClickListener { onItemClick(item.id) }
        }
    }

    override fun getFilter(): Filter {

        return dietFilter;
    }

    val dietFilter = object : Filter() {
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

            displayFoodList = results?.values as ArrayList<Food>
            notifyDataSetChanged()
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            displayFoodList.clear()
            if (constraint == null || constraint.isEmpty()) {
                displayFoodList.addAll(foodList)
            } else {
                for (item in foodList) {
                    if (item.foodName.toLowerCase().contains(constraint.toString().toLowerCase()))
                        displayFoodList.add(item)
                }
            }
            var results = FilterResults()
            results.values = displayFoodList
            results.count = displayFoodList.size
            return results
        }
    }
}
