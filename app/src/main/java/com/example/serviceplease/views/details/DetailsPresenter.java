package com.example.serviceplease.views.details;

import com.example.serviceplease.helpers.Utils;
import com.example.serviceplease.model.foodModel.FoodList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter {
    private DetailsView detailsView;

    public DetailsPresenter(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    void getMealById(String namesake) {

        detailsView.showLoading();

        Utils.getFoodApi().getMealsByName(namesake).enqueue(new Callback<FoodList>() {
            @Override
            public void onResponse(Call<FoodList> call, Response<FoodList> response) {
                detailsView.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    detailsView.setMeals(response.body().getMeals().get(0));
                } else {
                    detailsView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<FoodList> call, Throwable t) {
                detailsView.hideLoading();
                detailsView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
