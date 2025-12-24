package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.adapters.WallpaperAdapter;
import com.rtech.eazywalls.constants.WallpaperListType;
import com.rtech.eazywalls.databinding.FragmentHomeBinding;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.utils.GlideUtil;
import com.rtech.eazywalls.utils.PageNavigatorUtil;
import com.rtech.eazywalls.viewModels.FeaturedWallpaperViewModel;
import com.rtech.eazywalls.viewModels.TrendingWallpaperViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding mainXml;
    ArrayList<WallpaperModel> trendingWallpapers;
    ArrayList<WallpaperModel> featuredWallpapers;
    WallpaperAdapter wallpaperAdapter;
    TrendingWallpaperViewModel trendingWallpaperViewModel;
    FeaturedWallpaperViewModel featuredWallpaperViewModel;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainXml=FragmentHomeBinding.inflate(inflater,container,false);
        init();
        setUpRecyclerView();
        return mainXml.getRoot();
    }

    private void setUpRecyclerView() {
        mainXml.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mainXml.recyclerView.setAdapter(wallpaperAdapter);

    }

    private void init(){
        trendingWallpapers =new ArrayList<>();
        featuredWallpapers=new ArrayList<>();
        wallpaperAdapter=new WallpaperAdapter(requireContext(), trendingWallpapers, WallpaperListType.TRENDING.toString());
        trendingWallpaperViewModel=new ViewModelProvider(requireActivity()).get(TrendingWallpaperViewModel.class);
        featuredWallpaperViewModel=new ViewModelProvider(requireActivity()).get(FeaturedWallpaperViewModel.class);
        trendingWallpaperViewModel.getTrendingWallpapers().observe(requireActivity(),wallpaperList->{
            if(wallpaperList!=null){
                trendingWallpapers.clear();
                trendingWallpapers.addAll(wallpaperList);
                //TODO: add pagination logic and notify the range change
                wallpaperAdapter.notifyDataSetChanged();
                mainXml.TrendingShimmerLayout.setVisibility(View.GONE);
                mainXml.recyclerView.setVisibility(View.VISIBLE);
            }
        });
        featuredWallpaperViewModel.getFeaturedWallpapers().observe(requireActivity(),data->{
            if(data!=null){
                featuredWallpapers.clear();
                featuredWallpapers.addAll(data);
                setUptFeatured();

            }

        });
    }

    private void setUptFeatured() {
        if(featuredWallpapers.size()>2){
            GlideUtil.loadImage(requireActivity(),mainXml.featured1Image,featuredWallpapers.get(0).getPreviewUrl(), R.drawable.wallpaper_placeholder);
            GlideUtil.loadImage(requireActivity(),mainXml.featured2Image,featuredWallpapers.get(1).getPreviewUrl(), R.drawable.wallpaper_placeholder);
            GlideUtil.loadImage(requireActivity(),mainXml.featured3Image,featuredWallpapers.get(2).getPreviewUrl(), R.drawable.wallpaper_placeholder);
            mainXml.featured1Image.setOnClickListener(v->PageNavigatorUtil.openPreviewPage(requireActivity(),featuredWallpapers.get(0)));
            mainXml.featured2Image.setOnClickListener(v->PageNavigatorUtil.openPreviewPage(requireActivity(),featuredWallpapers.get(1)));
            mainXml.featured3Image.setOnClickListener(v->PageNavigatorUtil.openPreviewPage(requireActivity(),featuredWallpapers.get(2)));
        }
        if(featuredWallpapers.size()>3){
            int sum=featuredWallpapers.size()-3;
            mainXml.featuredImageCountText.setText("+"+sum);
            mainXml.moreFeaturedBtn.setOnClickListener(v->{
                //TODO:add work here
            });
        }
    }
}