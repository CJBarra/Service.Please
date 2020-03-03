package com.example.serviceplease.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.serviceplease.R;
import com.example.serviceplease.model.foodModel.FoodList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerHeaderAdapter extends PagerAdapter {

    //    private List<DrinkList.Drink> drinks;
    private List<FoodList.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public ViewPagerHeaderAdapter(List<FoodList.Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ViewPagerHeaderAdapter.clickListener = clickListener;
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view.equals(obj);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_view_pager_header, container, false
        );

        ImageView itemThumbnail = view.findViewById(R.id.itemThumb);
        TextView itemNamesake = view.findViewById(R.id.itemName);

        String strItemThumb = meals.get(position).getStrMealThumb();
        Picasso.get().load(strItemThumb).into(itemThumbnail);

        String strItemName = meals.get(position).getStrMeal();
        itemNamesake.setText(strItemName);


        view.setOnClickListener(v -> clickListener.onClick(v, position));
        container.addView(view, 0);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }
}
