package com.chandilsachin.diettracker.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.chandilsachin.diettracker.R
import com.chandilsachin.diettracker.database.DietFood
import ru.rambler.libs.swipe_layout.SwipeLayout

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

class DietListAdapter(context: android.content.Context) : RecyclerView.Adapter<DietListAdapter.ViewHolder>(){


    var foodList: List<DietFood> = emptyList()


    private val inflater = LayoutInflater.from(context)
    var onItemEditClick: (food: DietFood) -> Unit = {}
    var onItemDeleteClick: (food: DietFood) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.chandilsachin.diettracker.adapters.DietListAdapter.ViewHolder {
        val holder = ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindView(foodList[position])


    override fun getItemCount() = foodList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewFoodName = itemView.findViewById(R.id.textViewFoodName) as TextView
        private val textViewFoodDesc = itemView.findViewById(R.id.textViewFoodDesc) as TextView
        private val textViewQuantity = itemView.findViewById(R.id.textViewQuantity) as TextView
        private val swipeLayoutDietListItem = itemView.findViewById(R.id.swipeLayoutDietListItem) as SwipeLayout
        private val textViewDietItemDelete = itemView.findViewById(R.id.textViewDietItemDelete) as TextView
        private val textViewDietItemEdit = itemView.findViewById(R.id.textViewDietItemEdit) as TextView

        fun bindView(food: DietFood) {
            textViewFoodName.text = food.foodName
            textViewFoodDesc.text = food.foodDesc
            textViewQuantity.text = "x${food.quantity}"
            textViewQuantity.visibility = View.VISIBLE
            swipeLayoutDietListItem.isRightSwipeEnabled = true
            swipeLayoutDietListItem.isLeftSwipeEnabled = true

            textViewDietItemEdit.setOnClickListener {
                onItemEditClick(food)
                swipeLayoutDietListItem.reset()
            }

            textViewDietItemDelete.setOnClickListener(View.OnClickListener {
                onItemDeleteClick(food)
                swipeLayoutDietListItem.reset()
            })
        }
    }
}
