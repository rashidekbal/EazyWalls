package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.adapters.WallpaperAdapter;
import com.rtech.eazywalls.constants.WallpaperListType;
import com.rtech.eazywalls.databinding.FragmentPhotosResultFragmentBinding;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.viewModels.SearchViewModel;

import java.util.ArrayList;


public class Photos_result_fragment extends Fragment {
    FragmentPhotosResultFragmentBinding mainXml;
    ArrayList<WallpaperModel> wallpaperModels;
    WallpaperAdapter adapter;
    SearchViewModel searchViewModel;



    public Photos_result_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainXml=FragmentPhotosResultFragmentBinding.inflate(inflater,container,false);
        init();
        observerData();
        setUpRecyclerView();
        return mainXml.getRoot();
    }
    private void observerData(){
        searchViewModel.getWallpaperResult().observe(requireActivity(),data->{
            if(data!=null){
                wallpaperModels.clear();
                wallpaperModels.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void setUpRecyclerView(){
        mainXml.recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(),2));
        mainXml.recyclerView.setAdapter(adapter);
    }
    private void init(){
        searchViewModel=new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        wallpaperModels=new ArrayList<>();
        adapter=new WallpaperAdapter(requireActivity(),wallpaperModels, WallpaperListType.SEARCH.toString());

    }
}