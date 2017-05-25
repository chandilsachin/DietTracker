package com.chandilsachin.diettracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.database.Food

import java.util.ArrayList

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class FoodListAdapter(context: Context, var foodList: List<Food>, var onItemClick:(Long)-> Unit) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewFoodName.text = foodList[position].foodName
        holder.textViewFoodDesc.text = foodList[position].foodDesc

        holder.itemView.setOnClickListener { onItemClick(foodList[position].id) }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewFoodName: TextView
        var textViewFoodDesc: TextView

        init {
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName) as TextView
            textViewFoodDesc = itemView.findViewById(R.id.textViewFoodDesc) as TextView
        }
    }
}
