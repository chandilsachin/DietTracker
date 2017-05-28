package com.chandilsachin.diettracker.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.database.DietFood
import com.chandilsachin.diettracker.database.Food
import java.util.*

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class DietListAdapter(context: android.content.Context, val foodList: List<DietFood>) : android.support.v7.widget.RecyclerView.Adapter<DietListAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private var onItemClick: DietListAdapter.Callback<Void, Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.chandilsachin.diettracker.adapters.DietListAdapter.ViewHolder {
        val holder = ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindView(foodList[position])


    override fun getItemCount() = foodList.size

    fun setOnItemClick(onItemClick: Callback<Void, Long>) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewFoodName = itemView.findViewById(R.id.textViewFoodName) as TextView
        private val textViewFoodDesc = itemView.findViewById(R.id.textViewFoodDesc) as TextView
        private val textViewQuantity = itemView.findViewById(R.id.textViewQuantity) as TextView

        fun bindView(food:DietFood){
            textViewFoodName.text = food.foodName
            textViewFoodDesc.text = food.foodDesc
            textViewQuantity.text = "x${food.quantity}"
            itemView.setOnClickListener {
                if (onItemClick != null)
                    onItemClick!!.callback(foodList[position].id)
            }
        }
    }

    interface Callback<R, P> {
        fun callback(param: P): R
    }
}
