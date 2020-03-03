package com.example.serviceplease.api;

import com.example.serviceplease.model.foodModel.FoodCategoryList;
import com.example.serviceplease.model.foodModel.FoodList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodServiceApi {

    @GET("1/filter.php?c=miscellaneous")
    Call<FoodList> getMeal();

    @GET("1/categories.php")
    Call<FoodCategoryList> getCategory();

    @GET("1/filter.php")
    Call<FoodList> getMealsByCategory(@Query("c") String category);

    @GET("1/search.php")
    Call<FoodList> getMealsByName(@Query("s") String namesake);
}
