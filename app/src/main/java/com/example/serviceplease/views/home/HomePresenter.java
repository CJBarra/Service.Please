package com.example.serviceplease.views.home;

import androidx.annotation.NonNull;

import com.example.serviceplease.helpers.Utils;
import com.example.serviceplease.model.foodModel.FoodCategoryList;
import com.example.serviceplease.model.foodModel.FoodList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter {
    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
    }

//FOOD API METHODS

    void getMeals() {

        homeView.showLoading();

        Call<FoodList> foodListCall = Utils.getFoodApi().getMeal();

        foodListCall.enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(@NonNull Call<FoodList> call,
                                   @NonNull Response<FoodList> response) {
                homeView.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    homeView.setMeals(response.body().getMeals());
                } else {
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodList> call,
                                  @NonNull Throwable t) {
                homeView.hideLoading();
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    void getFoodCategories() {
        homeView.showLoading();

        Call<FoodCategoryList> foodCategoryListCall = Utils.getFoodApi().getCategory();

        foodCategoryListCall.enqueue(new Callback<FoodCategoryList>() {
            @Override
            public void onResponse(@NonNull Call<FoodCategoryList> call,
                                   @NonNull Response<FoodCategoryList> response) {
                homeView.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    homeView.setMealCategory(response.body().getCategories());
                } else {
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FoodCategoryList> call,
                                  @NonNull Throwable t) {
                homeView.hideLoading();
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }

    /*
    void getDrinks() {

        homeView.showLoading();

        Call<DrinkList> drinkListCall = Utils.getDrinkApi().getDrink();

        drinkListCall.enqueue(new Callback<DrinkList>() {
            @Override
            public void onResponse(@NonNull Call<DrinkList> call,
                                   @NonNull Response<DrinkList> response) {
                homeView.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    homeView.setDrinks(response.body().getDrinks());
                } else {
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DrinkList> call,
                                  @NonNull Throwable t) {
                homeView.hideLoading();
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    void getCategories() {
        homeView.showLoading();

        Call<DrinkCategoryList> categoryListCall = Utils.getDrinkApi().getCategory();

        categoryListCall.enqueue(new Callback<DrinkCategoryList>() {
            @Override
            public void onResponse(@NonNull Call<DrinkCategoryList> call,
                                   @NonNull Response<DrinkCategoryList> response) {
                homeView.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    homeView.setDrinkCategory(response.body().getCategories());
                } else {
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<DrinkCategoryList> call,
                                  @NonNull Throwable t) {
                homeView.hideLoading();
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
*/

}
