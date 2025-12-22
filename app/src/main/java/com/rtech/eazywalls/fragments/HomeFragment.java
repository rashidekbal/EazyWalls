package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.adapters.WallpaperAdapter;
import com.rtech.eazywalls.constants.WallpaperListType;
import com.rtech.eazywalls.databinding.FragmentHomeBinding;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.viewModels.TrendingWallpaperViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding mainXml;
    ArrayList<WallpaperModel> sampleData;
    WallpaperAdapter wallpaperAdapter;
    TrendingWallpaperViewModel trendingWallpaperViewModel;


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
        sampleData=new ArrayList<>();
        wallpaperAdapter=new WallpaperAdapter(requireContext(),sampleData, WallpaperListType.TRENDING.toString());
        trendingWallpaperViewModel=new ViewModelProvider(requireActivity()).get(TrendingWallpaperViewModel.class);
        trendingWallpaperViewModel.getTrendingWallpapers().observe(requireActivity(),wallpaperList->{
            if(wallpaperList!=null){
                sampleData.clear();
                sampleData.addAll(wallpaperList);
                wallpaperAdapter.notifyDataSetChanged();
            }
        });
    }
}