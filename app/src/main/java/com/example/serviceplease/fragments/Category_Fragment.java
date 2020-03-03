package com.example.serviceplease.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviceplease.R;
import com.example.serviceplease.helpers.RecyclerViewListByCategory;
import com.example.serviceplease.helpers.Utils;
import com.example.serviceplease.model.foodModel.FoodList;
import com.example.serviceplease.views.category.CategoryPresenter;
import com.example.serviceplease.views.category.CategoryView;
import com.example.serviceplease.views.details.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.serviceplease.views.home.HomeActivity.EXTRA_DETAIL;

public class Category_Fragment extends Fragment implements CategoryView {

    @BindView(R.id.recyclerViewLayout)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imageCategory)
    ImageView imageCategory;
    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;
    @BindView(R.id.textCategory)
    TextView textCategory;

    AlertDialog.Builder descDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            // TODO: FIX ALL THIS!
            Picasso.get().load(getArguments()
                    .getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategory);
            Picasso.get().load(getArguments()
                    .getString("EXTRA_DATA_IMAGE"))
                    .into(imageCategoryBg);

            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            ////            //declare presenter
            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealsByCategory(getArguments().getString("EXTRA_DATA_NAME"));
////            presenter.getDrinksByCategory(getArguments().getString("EXTRA_DATA_NAME"));
//
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<FoodList.Meal> meals) {
        RecyclerViewListByCategory adapter =
                new RecyclerViewListByCategory(getActivity(), meals);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            TextView itemName = view.findViewById(R.id.itemName);
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra(EXTRA_DETAIL, itemName.getText().toString());
            startActivity(intent);

        });
    }


    // TODO: CREATE setDrinks(List<DrinkList.Drink> drinks){}

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

    @OnClick(R.id.cardCategory)
    public void onClick() {
        descDialog.setPositiveButton("CLOSE", (dialog, which) -> dialog.dismiss());
        descDialog.show();
    }
}
