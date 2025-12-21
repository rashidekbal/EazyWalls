package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.adapters.WallpaperAdapter;
import com.rtech.eazywalls.databinding.FragmentHomeBinding;
import com.rtech.eazywalls.models.WallpaperModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding mainXml;
    ArrayList<WallpaperModel> sampleData;
    WallpaperAdapter wallpaperAdapter;


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
        wallpaperAdapter=new WallpaperAdapter(requireContext(),sampleData);
        mainXml.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mainXml.recyclerView.setAdapter(wallpaperAdapter);

    }

    private void init(){
        sampleData=new ArrayList<>();
        sampleData.add(new WallpaperModel(1,"https://img.freepik.com/free-photo/enchanted-forest-fantasy-background_23-2151910723.jpg",true));
        sampleData.add(new WallpaperModel(2,"https://img.freepik.com/free-vector/hand-painted-watercolour-tree-landscape-background_1048-18808.jpg",true));
        sampleData.add(new WallpaperModel(3,"https://img.freepik.com/free-photo/surreal-neon-tropical-flowers_23-2151665782.jpg",false));
        sampleData.add(new WallpaperModel(4,"https://img.freepik.com/free-photo/digital-art-moon-deer-wallpaper_23-2150918787.jpg",true));
        sampleData.add(new WallpaperModel(5,"https://img.freepik.com/free-photo/digital-art-moon-man-silhouette-wallpaper_23-2150918889.jpg",false));
        sampleData.add(new WallpaperModel(6,"https://img.freepik.com/free-photo/beautiful-domestic-cat-laying-fence_181624-43207.jpg",true));

    }
}