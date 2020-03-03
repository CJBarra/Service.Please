package com.example.serviceplease.views.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.serviceplease.R;
import com.example.serviceplease.helpers.ViewPagerCategoryAdapter;
import com.example.serviceplease.model.foodModel.FoodCategoryList;
import com.example.serviceplease.views.home.HomeActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPagerLayout)
    ViewPager viewPagerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        initActionBar();
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<FoodCategoryList.Category> categories =
                (List<FoodCategoryList.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);

        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);

        ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(
                getSupportFragmentManager(),
                categories);

        viewPagerLayout.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPagerLayout);
        viewPagerLayout.setCurrentItem(position, true);
        adapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
