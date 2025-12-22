package com.rtech.eazywalls.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.adapters.WallpaperAdapter;
import com.rtech.eazywalls.constants.CategoryIntentKeys;
import com.rtech.eazywalls.constants.WallpaperListType;
import com.rtech.eazywalls.databinding.ActivityCategoryBinding;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.utils.ToastUtil;
import com.rtech.eazywalls.viewModels.WallpapersViewModel;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding mainXml;
    WallpapersViewModel wallpapersViewModel;
    ArrayList<WallpaperModel> wallpaperModels;
    WallpaperAdapter wallpaperAdapter;
    CategoryModel category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivityCategoryBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(mainXml.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setUpPageUi();
        setUpClickListeners();
        setUpRecyclerView();
    }

    private void setUpPageUi() {
        mainXml.categoryTitle.setText(category.getTitle());
        mainXml.wallpaperCountTitle.setText(getCountTitle(category.getWallpaperCount()));

    }
    private StringBuilder getCountTitle(int wallpaperCount) {
        StringBuilder builder=new StringBuilder();
        builder.append(wallpaperCount);
        builder.append(" listed wallpapers from abstract collection.");
        return builder;

    }

    private void setUpClickListeners() {
        mainXml.backBtn.setOnClickListener(v->{
            finish();
        });
    }

    private void setUpRecyclerView() {
        mainXml.recyclerView.setAdapter(wallpaperAdapter);
        mainXml.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

    }
    private void init(){
        Intent data=getIntent();
        if(data==null){
            ToastUtil.showToast(this,"something went wrong");
            finish();
            return;
        }
        category=new CategoryModel(
                data.getIntExtra(CategoryIntentKeys.ID.toString(),0),
                data.getStringExtra(CategoryIntentKeys.TITLE.toString()),
                data.getStringExtra(CategoryIntentKeys.URL.toString()),
                data.getIntExtra(CategoryIntentKeys.WALLPAPERS_COUNT.toString(),0));

        wallpaperModels=new ArrayList<>();
        wallpaperAdapter=new WallpaperAdapter(this,wallpaperModels, WallpaperListType.COLLECTION.toString());
        wallpapersViewModel=new ViewModelProvider(this).get(WallpapersViewModel.class);
        wallpapersViewModel.getWallpapersLiveData(category.getTitle()).observe(this, wallpapersList->{
            if(wallpapersList!=null){
                wallpaperModels.clear();
                wallpaperModels.addAll(wallpapersList);
                wallpaperAdapter.notifyDataSetChanged();

            }

        });
    }
}