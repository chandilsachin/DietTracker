package com.chandilsachin.diettracker

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class DietListAdapter(context: Context, val foodList: ArrayList<Food>) : RecyclerView.Adapter<DietListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater
    private var onItemClick: Callback<Void, Long>? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DietListAdapter.ViewHolder {
        val holder = ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: DietListAdapter.ViewHolder, position: Int) {
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

    fun setOnItemClick(onItemClick: Callback<Void, Long>) {
        this.onItemClick = onItemClick
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewFoodName: TextView
        var textViewFoodDesc: TextView

        init {
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName) as TextView
            textViewFoodDesc = itemView.findViewById(R.id.textViewFoodDesc) as TextView
        }
    }

    interface Callback<R, P> {
        fun callback(param: P): R
    }
}
