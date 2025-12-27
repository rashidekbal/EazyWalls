package com.rtech.eazywalls.activities;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.adapters.WallpaperAdapter;
import com.rtech.eazywalls.constants.WallpaperListType;
import com.rtech.eazywalls.databinding.ActivityFeaturedFeedBinding;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.viewModels.FeaturedWallpaperViewModel;

import java.util.ArrayList;

public class FeaturedFeedActivity extends AppCompatActivity {
    ActivityFeaturedFeedBinding mainXml;
    FeaturedWallpaperViewModel wallpaperViewModel;
    ArrayList<WallpaperModel> wallpapers;
    WallpaperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivityFeaturedFeedBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(mainXml.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setUpWallpapers();
        setUpRecyclerView();
        handleClickListeners();
    }
    private void init(){
        wallpaperViewModel=new ViewModelProvider(this).get(FeaturedWallpaperViewModel.class);
        wallpapers=new ArrayList<>();
        adapter=new WallpaperAdapter(this,wallpapers, WallpaperListType.COLLECTION.toString());
    }
    private void setUpWallpapers(){
        wallpaperViewModel.getFeaturedWallpapers().observe(this,data->{
            mainXml.swipeRefreshLayout.setRefreshing(false);
            if(data!=null){
                if(data.isEmpty()){
                    mainXml.reloadView.setVisibility(View.VISIBLE);
                }else{
                    mainXml.recyclerView.setVisibility(View.VISIBLE);
                    mainXml.reloadView.setVisibility(View.GONE);
                    wallpapers.clear();
                    wallpapers.addAll(data);
                    adapter.notifyDataSetChanged();
                    mainXml.wallpaperCountTitle.setText(getCountTitle(data.size()));

                }
                mainXml.shimmerLayout.setVisibility(View.GONE);
            }

        });

    }
    private void setUpRecyclerView(){
        mainXml.recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mainXml.recyclerView.setAdapter(adapter);
    }
    private void handleClickListeners(){
        mainXml.reloadView.setOnClickListener(v->{
            mainXml.shimmerLayout.setVisibility(View.VISIBLE);
            mainXml.reloadView.setVisibility(View.GONE);
            wallpaperViewModel.loadWallpapers();
        });
        mainXml.swipeRefreshLayout.setOnRefreshListener(()->{
            mainXml.swipeRefreshLayout.setRefreshing(true);
            wallpaperViewModel.loadWallpapers();
        });
        mainXml.backBtn.setOnClickListener(v->{
            finish();
        });

    }
    private StringBuilder getCountTitle(int wallpaperCount) {
        StringBuilder builder=new StringBuilder();
        builder.append(wallpaperCount);
        builder.append(" listed wallpapers from featured collection.");
        return builder;

    }
}