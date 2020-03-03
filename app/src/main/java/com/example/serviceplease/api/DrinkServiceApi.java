package com.example.serviceplease.api;

import com.example.serviceplease.model.drinkModel.DrinkCategoryList;
import com.example.serviceplease.model.drinkModel.DrinkList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrinkServiceApi {

    @GET("1/random.php")
    Call<DrinkList> getDrink();

    @GET("1/list.php?c=list")
    Call<DrinkCategoryList> getCategory();

    @GET("1/filter.php")
    Call<DrinkList> getDrinksByCategory(@Query("c") String category);
}
