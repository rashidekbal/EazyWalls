package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.adapters.CategoryAdapter;
import com.rtech.eazywalls.databinding.FragmentCategoryBinding;
import com.rtech.eazywalls.models.CategoryModel;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
FragmentCategoryBinding mainXml;
ArrayList<CategoryModel> categoryModels;
CategoryAdapter categoryAdapter;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainXml=FragmentCategoryBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        init();
        setUpRecyclerView();
        return mainXml.getRoot();
    }

    private void setUpRecyclerView() {
        mainXml.recyclerView.setAdapter(categoryAdapter);
        mainXml.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));

    }

    private void init() {
        categoryModels=new ArrayList<>();
        categoryModels.add(new CategoryModel(1,"Abstract","https://img.freepik.com/free-photo/enchanted-forest-fantasy-background_23-2151910723.jpg",640));
        categoryModels.add(new CategoryModel(2,"Architect","https://img.freepik.com/free-vector/hand-painted-watercolour-tree-landscape-background_1048-18808.jpg",480));
        categoryModels.add(new CategoryModel(3,"ColorFull","https://img.freepik.com/free-photo/surreal-neon-tropical-flowers_23-2151665782.jpg",600));
        categoryModels.add(new CategoryModel(4,"Nature","https://img.freepik.com/free-photo/digital-art-moon-deer-wallpaper_23-2150918787.jpg",225));
        categoryModels.add(new CategoryModel(5,"Galaxy","https://img.freepik.com/free-photo/digital-art-moon-man-silhouette-wallpaper_23-2150918889.jpg",442));
        categoryModels.add(new CategoryModel(6,"Mountains","https://img.freepik.com/free-photo/beautiful-domestic-cat-laying-fence_181624-43207.jpg",225));
        categoryAdapter=new CategoryAdapter(requireContext(),categoryModels);


    }
}