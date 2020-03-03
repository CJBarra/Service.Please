package com.example.serviceplease.views.category;

import com.example.serviceplease.model.foodModel.FoodList;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
//    void setDrinks(List<DrinkList.Drink> drinks);
    void setMeals(List<FoodList.Meal> meals);
    void onErrorLoading(String message);
}
