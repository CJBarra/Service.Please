package com.example.serviceplease.views.details;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import com.example.serviceplease.R;
import com.example.serviceplease.helpers.Utils;
import com.example.serviceplease.model.foodModel.FoodList;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.serviceplease.views.home.HomeActivity.EXTRA_DETAIL;

public class DetailsActivity extends AppCompatActivity implements DetailsView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapse_toolbar;
    @BindView(R.id.itemThumb)
    ImageView itemThumb;
    @BindView(R.id.categoryLoading)
    TextView categoryLoading;
    @BindView(R.id.countryLoading)
    TextView countryLoading;
    @BindView(R.id.instructions)
    TextView instructions;
    @BindView(R.id.ingredient)
    TextView ingredient;
    @BindView(R.id.measurements)
    TextView measurements;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.youtube)
    TextView youtube;
    @BindView(R.id.source)
    TextView source;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        setupActionBar();

        Intent intent = getIntent();
        String itemName = intent.getStringExtra(EXTRA_DETAIL);

        DetailsPresenter presenter = new DetailsPresenter(this);
        presenter.getMealById(itemName);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapse_toolbar.setContentScrimColor(getResources().getColor(R.color.colorTextPrimary));
        collapse_toolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapse_toolbar.setExpandedTitleColor(getResources().getColor(R.color.colorTextPrimary));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem favouriteItem = menu.findItem(R.id.favourite);
        Drawable favouriteItemColor = favouriteItem.getIcon();
        setupColorActionBarIcon(favouriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupColorActionBarIcon(Drawable favouriteItemColor) {
        appbar.addOnOffsetChangedListener((appbar, verticalOffset) -> {
            if ((collapse_toolbar.getHeight() + verticalOffset) < (2 * ViewCompat
                    .getMinimumHeight(collapse_toolbar))) {
                if (toolbar.getNavigationIcon() != null) {
                    toolbar.getNavigationIcon().setColorFilter(getResources()
                            .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                    favouriteItemColor.mutate().setColorFilter(getResources()
                            .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                }
            } else {
                if (toolbar.getNavigationIcon() != null) {
                    toolbar.getNavigationIcon().setColorFilter(getResources()
                            .getColor(R.color.colorTextPrimary), PorterDuff.Mode.SRC_ATOP);
                    favouriteItemColor.mutate().setColorFilter(getResources()
                            .getColor(R.color.colorTextPrimary), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMeals(FoodList.Meal meals) {
//        Log.w("TAG", meals.getStrMeal());
        Picasso.get().load(meals.getStrMealThumb()).into(itemThumb);
        collapse_toolbar.setTitle(meals.getStrMeal());
        categoryLoading.setText(meals.getStrCategory());
        countryLoading.setText(meals.getStrArea());
        instructions.setText(meals.getStrInstructions());
        setupActionBar();

        // TODO: Check for empty fields in API json (YOUTUBE & SRC)
        youtube.setOnClickListener(view -> {
            Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
            intentYoutube.setData(Uri.parse(meals.getStrYoutube()));
            startActivity(intentYoutube);
        });

        source.setOnClickListener(v -> {
            Intent intentSource = new Intent(Intent.ACTION_VIEW);
            intentSource.setData(Uri.parse(meals.getStrSource()));
            startActivity(intentSource);
        });


        // TODO: Check for empty fields in API json (INGREDIENTS)
        if (!meals.getStrIngredient1().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient1());
        }
        if (!meals.getStrIngredient2().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient2());
        }
        if (!meals.getStrIngredient3().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient3());
        }
        if (!meals.getStrIngredient4().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient4());
        }
        if (!meals.getStrIngredient5().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient5());
        }
        if (!meals.getStrIngredient6().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient6());
        }
        if (!meals.getStrIngredient7().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient7());
        }
        if (!meals.getStrIngredient8().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient8());
        }
        if (!meals.getStrIngredient9().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient9());
        }
        if (!meals.getStrIngredient10().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient10());
        }
        if (!meals.getStrIngredient11().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient11());
        }
        if (!meals.getStrIngredient12().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient12());
        }
        if (!meals.getStrIngredient13().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient13());
        }
        if (!meals.getStrIngredient14().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient14());
        }
        if (!meals.getStrIngredient15().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient15());
        }
        if (!meals.getStrIngredient16().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient16());
        }
        if (!meals.getStrIngredient17().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient17());
        }
        if (!meals.getStrIngredient18().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient18());
        }
        if (!meals.getStrIngredient19().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient19());
        }
        if (!meals.getStrIngredient20().isEmpty()) {
            ingredient.append("\n \u2022 " + meals.getStrIngredient20());
        }

        // TODO: Check for empty fields in API json (INGREDIENTS)
        if (!meals.getStrMeasure1().isEmpty() && !Character.isWhitespace(meals.getStrMeasure1().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure1());
        }
        if (!meals.getStrMeasure2().isEmpty() && !Character.isWhitespace(meals.getStrMeasure2().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure2());
        }
        if (!meals.getStrMeasure3().isEmpty() && !Character.isWhitespace(meals.getStrMeasure3().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure3());
        }
        if (!meals.getStrMeasure4().isEmpty() && !Character.isWhitespace(meals.getStrMeasure4().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure4());
        }
        if (!meals.getStrMeasure5().isEmpty() && !Character.isWhitespace(meals.getStrMeasure5().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure5());
        }
        if (!meals.getStrMeasure6().isEmpty() && !Character.isWhitespace(meals.getStrMeasure6().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure6());
        }
        if (!meals.getStrMeasure7().isEmpty() && !Character.isWhitespace(meals.getStrMeasure7().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure7());
        }
        if (!meals.getStrMeasure8().isEmpty() && !Character.isWhitespace(meals.getStrMeasure8().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure8());
        }
        if (!meals.getStrMeasure9().isEmpty() && !Character.isWhitespace(meals.getStrMeasure9().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure9());
        }
        if (!meals.getStrMeasure10().isEmpty() && !Character.isWhitespace(meals.getStrMeasure10().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure10());
        }
        if (!meals.getStrMeasure11().isEmpty() && !Character.isWhitespace(meals.getStrMeasure11().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure11());
        }
        if (!meals.getStrMeasure12().isEmpty() && !Character.isWhitespace(meals.getStrMeasure12().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure12());
        }
        if (!meals.getStrMeasure13().isEmpty() && !Character.isWhitespace(meals.getStrMeasure13().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure13());
        }
        if (!meals.getStrMeasure14().isEmpty() && !Character.isWhitespace(meals.getStrMeasure14().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure14());
        }
        if (!meals.getStrMeasure15().isEmpty() && !Character.isWhitespace(meals.getStrMeasure15().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure15());
        }
        if (!meals.getStrMeasure16().isEmpty() && !Character.isWhitespace(meals.getStrMeasure16().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure16());
        }
        if (!meals.getStrMeasure17().isEmpty() && !Character.isWhitespace(meals.getStrMeasure17().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure17());
        }
        if (!meals.getStrMeasure18().isEmpty() && !Character.isWhitespace(meals.getStrMeasure18().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure18());
        }
        if (!meals.getStrMeasure19().isEmpty() && !Character.isWhitespace(meals.getStrMeasure19().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure19());
        }
        if (!meals.getStrMeasure20().isEmpty() && !Character.isWhitespace(meals.getStrMeasure20().charAt(0))) {
            measurements.append("\n : " + meals.getStrMeasure20());
        }
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error", message);
    }
}
