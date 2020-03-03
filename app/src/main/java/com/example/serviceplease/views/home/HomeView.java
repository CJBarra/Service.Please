package com.example.serviceplease.views.home;

import com.example.serviceplease.model.foodModel.FoodCategoryList;
import com.example.serviceplease.model.foodModel.FoodList;

import java.util.List;

public interface HomeView {
    void showLoading();
    void hideLoading();
//    void setDrinks(List<DrinkList.Drink> drinks);
    void setMeals(List<FoodList.Meal> meals);
//    void setDrinkCategory(List<DrinkCategoryList.Category> categoryDrink);
    void setMealCategory(List<FoodCategoryList.Category> categoryFood);
    void onErrorLoading(String message);
}
