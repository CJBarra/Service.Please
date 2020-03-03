package com.example.serviceplease.helpers;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.serviceplease.fragments.Category_Fragment;
import com.example.serviceplease.model.foodModel.FoodCategoryList;

import java.util.List;

public class ViewPagerCategoryAdapter extends FragmentStatePagerAdapter {

    //    private List<DrinkCategoryList.Category> categories;
    private List<FoodCategoryList.Category> categories;

    public ViewPagerCategoryAdapter(FragmentManager fm, List<FoodCategoryList.Category> categories) {
        super(fm);
        this.categories = categories;
    }

    public Fragment getItem(int i) {
        Category_Fragment fragment = new Category_Fragment();
        Bundle args = new Bundle();
        args.putString("EXTRA_DATA_NAME", categories.get(i).getStrCategory());
        args.putString("EXTRA_DATA_DESC", categories.get(i).getStrCategoryDescription());
        args.putString("EXTRA_DATA_IMAGE", categories.get(i).getStrCategoryThumb());

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    @Nullable
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getStrCategory();
    }
}
