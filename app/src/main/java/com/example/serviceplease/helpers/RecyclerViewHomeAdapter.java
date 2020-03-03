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
import com.example.serviceplease.model.foodModel.FoodCategoryList;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    //    private List<DrinkCategoryList.Category> categories;
    private List<FoodCategoryList.Category> categories;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewHomeAdapter(List<FoodCategoryList.Category> categories, Context context) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public RecyclerViewHomeAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.item_recycler_category, viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHomeAdapter.RecyclerViewHolder viewHolder, int i) {
        String strCategoryThumb = categories.get(i).getStrCategoryThumb();
        Picasso.get().load(strCategoryThumb)
                .placeholder(R.drawable.ic_circle_24dp)
                .into(viewHolder.categoryThumb);

        String strCategoryName = categories.get(i).getStrCategory();
        viewHolder.categoryName.setText(strCategoryName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.itemThumb)
        ImageView categoryThumb;

        @BindView(R.id.itemName)
        TextView categoryName;

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
        RecyclerViewHomeAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

}
