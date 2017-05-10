package com.chandilsachin.diettracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chandilsachin.diettracker.mvp.view.ProgressDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = DatabaseManager.getInstance(this);
        presenter = new MainPresenter();
        initViews();
        setEvents();
        setUpDatabase();
        prepareDietList();
    }

    private void setUpDatabase(){
        if(!new InitPreferences(this).hasDataLoaded()){
            new SetUpFoodDatabase().execute();
        }
    }

    private void prepareDietList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<Food> list = presenter.getDietList(MainActivity.this);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bindDataToList(list);
                    }
                });
            }
        }).start();

    }


    private void bindDataToList(ArrayList<Food> list){
        DietListAdapter adapter = new DietListAdapter(this, list);
        recyclerViewDietList.setAdapter(adapter);
    }

    private RelativeLayout relativeLayoutAddBreakfast;
    private CardView linearLayoutBreakfast;
    private RecyclerView recyclerViewDietList;
    public void initViews(){
        linearLayoutBreakfast = (CardView) findViewById(R.id.linearLayoutBreakfast);
        relativeLayoutAddBreakfast = (RelativeLayout) linearLayoutBreakfast.findViewById(R.id.relativeLayoutAddFood);
        recyclerViewDietList = (RecyclerView) findViewById(R.id.recyclerViewDietList);
        recyclerViewDietList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setEvents(){
        relativeLayoutAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFoodActivity.class);
                startActivityForResult(intent, AddFoodActivity.CODE_FOOD_SELECTION);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case AddFoodActivity.CODE_FOOD_SELECTION:
                int selectedFood = data.getIntExtra(AddFoodActivity.SELECTED_INDEX, -1);
                dbManager.saveFood(selectedFood, 1f, Calendar.getInstance().getTime());
                Toast.makeText(this, "Selected Item: "+ selectedFood, Toast.LENGTH_SHORT).show();
                prepareDietList();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    class SetUpFoodDatabase extends AsyncTask<Void, Integer, Void>{

        android.app.ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "Preparing Database", "Preparing food database...", true);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            new InitPreferences(MainActivity.this).setDataHasLoaded(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                ArrayList<Food> list = new AddFoodPresenter().readFoodItemsFile(MainActivity.this);
                dbManager.addFoodList(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}