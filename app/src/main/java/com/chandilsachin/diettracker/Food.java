package com.chandilsachin.diettracker;

/**
 * Created by Sachin Chandil on 29/04/2017.
 */

public class Food {
    public int foodId;
    public String foodName;
    public String foodDesc;
    public double protein;
    public double carbs;
    public double fat;
    public double calories;

    public Food(String foodName, String foodDesc, double protein, double carbs, double fat) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        //this.calories = calories;
    }
}
