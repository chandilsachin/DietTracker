package com.chandilsachin.diettracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chandilsachin.diettracker.database.Food;

import java.util.ArrayList;

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Food> foodList;
    private Callback<Void, Integer> onItemClick;

    public FoodListAdapter(Context context, ArrayList<Food> list) {
        inflater = LayoutInflater.from(context);
        this.foodList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.layout_food_list_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textViewFoodName.setText(foodList.get(position).getFoodName());
        holder.textViewFoodDesc.setText(foodList.get(position).getFoodDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.callback(foodList.get(position).getFoodId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setOnItemClick(Callback<Void, Integer> onItemClick) {
        this.onItemClick = onItemClick;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewFoodName;
        public TextView textViewFoodDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewFoodName = (TextView) itemView.findViewById(R.id.textViewFoodName);
            textViewFoodDesc = (TextView) itemView.findViewById(R.id.textViewFoodDesc);
        }
    }

    public interface Callback<R,P> {
        public R callback(P param);
    }
}
