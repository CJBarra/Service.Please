package com.example.serviceplease.views.category;

import androidx.annotation.NonNull;

import com.example.serviceplease.helpers.Utils;
import com.example.serviceplease.model.foodModel.FoodList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

    public void getMealsByCategory(String category) {

        view.showLoading();
        Call<FoodList> foodListCall = Utils.getFoodApi().getMealsByCategory(category);

        foodListCall.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(@NonNull Call<FoodList> call, @NonNull Response<FoodList> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.setMeals(response.body().getMeals());
                } else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodList> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    /*
    TODO: FIND COCKTAIL API WORK-AROUND FOR NULL CATEGORY THUMBNAILS (CAUSES APP CRASH)

    public void getDrinkByCategory(String category) {

        view.showLoading();
        Call<DrinkList> drinkListCall = Utils.getDrinkApi().getDrinkByCategory(category);

        drinkListCall.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(@NonNull Call<DrinkList> call, @NonNull Response<DrinkList> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.setDrinks(response.body().getDrinks());
                }else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DrinkList> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
*/

}
