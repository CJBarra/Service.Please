package com.example.serviceplease.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceplease.R;
import com.example.serviceplease.model.foodModel.FoodList;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewListByCategory extends RecyclerView
        .Adapter<RecyclerViewListByCategory.RecyclerViewHolder> {

    //    private List<DrinkList.Drink> drinks;
    private List<FoodList.Meal> meals;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewListByCategory(Context context, List<FoodList.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewListByCategory.RecyclerViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_category,
                        parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        String strDrinkThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strDrinkThumb)
                .placeholder(R.drawable.shadow_gradient_bottom_top)
                .into(holder.itemThumbnail);

        String strDrinkName = meals.get(i).getStrMeal();
        holder.itemNamesake.setText(strDrinkName);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        @BindView(R.id.itemThumb)
        ImageView itemThumbnail;
        @BindView(R.id.itemName)
        TextView itemNamesake;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewListByCategory.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
