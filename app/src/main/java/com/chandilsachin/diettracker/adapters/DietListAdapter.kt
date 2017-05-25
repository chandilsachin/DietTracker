package com.chandilsachin.diettracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chandilsachin.diettracker.database.Food
import java.util.*

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class DietListAdapter(context: android.content.Context, val foodList: List<Food>) : android.support.v7.widget.RecyclerView.Adapter<DietListAdapter.ViewHolder>() {

    private val inflater: android.view.LayoutInflater
    private var onItemClick: com.chandilsachin.diettracker.adapters.DietListAdapter.Callback<Void, Long>? = null

    init {
        inflater = android.view.LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): com.chandilsachin.diettracker.adapters.DietListAdapter.ViewHolder {
        val holder = ViewHolder(inflater.inflate(com.chandilsachin.diettracker.R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: com.chandilsachin.diettracker.adapters.DietListAdapter.ViewHolder, position: Int) {
        holder.textViewFoodName.text = foodList[position].foodName
        holder.textViewFoodDesc.text = foodList[position].foodDesc

        holder.itemView.setOnClickListener {
            if (onItemClick != null)
                onItemClick!!.callback(foodList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun setOnItemClick(onItemClick: com.chandilsachin.diettracker.adapters.DietListAdapter.Callback<Void, Long>) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(itemView: android.view.View) : android.support.v7.widget.RecyclerView.ViewHolder(itemView) {

        var textViewFoodName: android.widget.TextView
        var textViewFoodDesc: android.widget.TextView

        init {
            textViewFoodName = itemView.findViewById(com.chandilsachin.diettracker.R.id.textViewFoodName) as android.widget.TextView
            textViewFoodDesc = itemView.findViewById(com.chandilsachin.diettracker.R.id.textViewFoodDesc) as android.widget.TextView
        }
    }

    interface Callback<R, P> {
        fun callback(param: P): R
    }
}
