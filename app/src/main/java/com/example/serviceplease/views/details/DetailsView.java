package com.example.serviceplease.views.details;

import com.example.serviceplease.model.foodModel.FoodList;

public interface DetailsView {
    void showLoading();
    void hideLoading();
    void setMeals(FoodList.Meal meals);
    void onErrorLoading(String message);
}
