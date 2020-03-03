package com.example.serviceplease.model.drinkModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DrinkCategoryList implements Serializable {

    @SerializedName("drinks")
    @Expose
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setDrinks(List<Category> categories) {
        this.categories = categories;
    }

    public class Category implements Serializable {

        @SerializedName("strCategory")
        @Expose
        private String strCategory;

        public String getStrCategory() {
            return strCategory;
        }

        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }

    }
}
