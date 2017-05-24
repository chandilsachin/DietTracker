package com.chandilsachin.diettracker;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.JsonReader;

import com.chandilsachin.diettracker.Food;
import com.chandilsachin.diettracker.io.JSONReader;
import com.ne1c.rainbowmvp.ViewState;
import com.ne1c.rainbowmvp.ViewStateListener;
import com.ne1c.rainbowmvp.base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

public class AddFoodPresenter extends BasePresenter<AddFoodView> implements ViewStateListener {
    public static final String TAG = AddFoodPresenter.class.getName();

    public AddFoodPresenter() {
        //addViewStateListener(this);
    }

    public ArrayList<Food> readFoodItemsFile(Context context) throws IOException {

        ArrayList<Food> list = new ArrayList<>();
        try {
            JSONArray jsonArray;
            jsonArray = JSONReader.readJsonFileToArrayFromAssets(context.getAssets(), "food_Items.json");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject fact = object.getJSONObject("facts");
                Food food = new Food(object.getString("food_name"), object.getString("food_desc"),fact.getDouble("protein"),
                        fact.getDouble("carbs"),fact.getDouble("fat"));
                list.add(food);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Food> readFoodItems(Context context) throws IOException {

        ArrayList<Food> list = DatabaseManager.getInstance(context).getAllFoodList();
        addFoodView.populateListItems(list);
        return list;
    }

    private AddFoodView addFoodView;
    @Override
    public void bindView(@NonNull AddFoodView view) {
        super.bindView(view);
        addFoodView = view;
    }

    @Override
    public void stateChanged(ViewState viewState) {

    }
}
