package com.example.serviceplease.views.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.serviceplease.R;
import com.example.serviceplease.helpers.RecyclerViewHomeAdapter;
import com.example.serviceplease.helpers.Utils;
import com.example.serviceplease.helpers.ViewPagerHeaderAdapter;
import com.example.serviceplease.model.foodModel.FoodCategoryList;
import com.example.serviceplease.model.foodModel.FoodList;
import com.example.serviceplease.views.category.CategoryActivity;
import com.example.serviceplease.views.details.DetailsActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    @BindView(R.id.viewPageHeader)
    ViewPager viewPager;

    @BindView(R.id.recyclerCategories)
    RecyclerView recyclerViewCategory;

    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        homePresenter = new HomePresenter(this);
        homePresenter.getMeals();
        homePresenter.getFoodCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerItem).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategories).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerItem).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategories).setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<FoodList.Meal> meals) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meals, this);
        viewPager.setAdapter(headerAdapter);
        viewPager.setPadding(20, 0, 150, 0);

        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView itemName = v.findViewById(R.id.itemName);
            Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            intent.putExtra(EXTRA_DETAIL, itemName.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void setMealCategory(List<FoodCategoryList.Category> category) {

        RecyclerViewHomeAdapter viewHomeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(viewHomeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this, 3, GridLayoutManager.VERTICAL, false);

        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);

        viewHomeAdapter.notifyDataSetChanged();

        viewHomeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

//    SET DRINK AND SET DRINK CATEGORY METHODS

    /*

    @Override
    public void setDrinks(List<DrinkList.Drink> drinks) {
//        TODO: check for connection to API
//        for (DrinkList.Drink drinkResult : drinks){
//            Log.w("drink name : ", drinkResult.getStrDrink());
//        }
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(drinks, this);
        viewPager.setAdapter(headerAdapter);
        viewPager.setPadding(20, 0, 150, 0);

        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            Toast.makeText(this,
                    drinks.get(position).getStrDrink(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void setDrinkCategory(List<DrinkCategoryList.Category> category) {

        RecyclerViewHomeAdapter viewHomeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(viewHomeAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this, 3, GridLayoutManager.VERTICAL, false);

        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);

        viewHomeAdapter.notifyDataSetChanged();

        viewHomeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

     */

}
