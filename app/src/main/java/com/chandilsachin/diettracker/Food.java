package com.chandilsachin.diettracker;

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

public class Food {
    public int foodId;
    public String foodName;
    public String foodDesc;
    public float protein;
    public float carbs;
    public float fat;
    public float calories;

    public Food(String foodName, String foodDesc, float protein, float carbs, float fat) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        //this.calories = calories;
    }
}
