package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.adapters.CategoryAdapter;
import com.rtech.eazywalls.databinding.FragmentCategoryBinding;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.viewModels.CategoryViewModel;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
FragmentCategoryBinding mainXml;
ArrayList<CategoryModel> categoryModels;
CategoryAdapter categoryAdapter;
CategoryViewModel categoryViewModel;
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
        categoryViewModel=new ViewModelProvider(requireActivity()).get(CategoryViewModel.class);
        categoryAdapter=new CategoryAdapter(requireContext(),categoryModels);
        categoryViewModel.getCategoryLiveData().observe(requireActivity(),categoryList->{
            if(categoryList!=null){
                categoryModels.clear();
                categoryModels.addAll(categoryList);
                categoryAdapter.notifyDataSetChanged();
                mainXml.shimmerLayout.setVisibility(View.GONE);
                mainXml.recyclerView.setVisibility(View.VISIBLE);
            }

        });


    }
}