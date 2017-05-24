package com.chandilsachin.diettracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.chandilsachin.diettracker.Food;

import java.io.IOException;
import java.util.ArrayList;

public class AddFoodActivity extends AppCompatActivity implements AddFoodView {

    public static final String SELECTED_FOOD_ID = "selectedIndex";
    public static final int CODE_FOOD_SELECTION = 1;

    /*@BindView(R.id.recyclerViewFoodList)*/
    RecyclerView recyclerViewFoodList;

//    @BindView(R.id.editTextSearchFood)
    EditText editTextSearchFood;

    @Override
    protected void onStart() {
        super.onStart();
        //getPresenter().bindView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //getPresenter().unbindView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        recyclerViewFoodList = (RecyclerView) findViewById(R.id.recyclerViewFoodList);
        recyclerViewFoodList.setLayoutManager(new LinearLayoutManager(this));
        prepareFoodItemList();

    }

    public void prepareFoodItemList()
    {
        try {
            AddFoodPresenter addFoodPresenter = new AddFoodPresenter();
            addFoodPresenter.bindView(this);
            addFoodPresenter.readFoodItems(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_add_food, menu);
        return super.onCreateOptionsMenu(menu);
    }*/



    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void populateListItems(ArrayList<Food> list) {
        FoodListAdapter adapter = new FoodListAdapter(this, list);
        adapter.setOnItemClick(new FoodListAdapter.Callback<Void, Long>() {
            @Override
            public Void callback(Long foodId) {
                Intent intent = new Intent(AddFoodActivity.this, FoodDetailsActivity.class);
                intent.putExtra(SELECTED_FOOD_ID, foodId);
                //setResult(CODE_FOOD_SELECTION, intent);
                startActivity(intent);
                return null;
            }
        });
        recyclerViewFoodList.setAdapter(adapter);
    }
}