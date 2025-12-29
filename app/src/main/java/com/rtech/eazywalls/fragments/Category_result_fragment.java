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
import com.rtech.eazywalls.databinding.FragmentCategoryResultFragmentBinding;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.viewModels.SearchViewModel;

import java.util.ArrayList;

public class Category_result_fragment extends Fragment {
    FragmentCategoryResultFragmentBinding mainXml;
    ArrayList<CategoryModel> categoryModels;
    CategoryAdapter adapter;
    SearchViewModel searchViewModel;


    public Category_result_fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainXml=FragmentCategoryResultFragmentBinding.inflate(inflater,container,false);
        init();
        observerData();
        setUpRecyclerView();
        return mainXml.getRoot();
    }
    private void observerData(){
        searchViewModel.getCategoryResult().observe(requireActivity(),data->{
            if(data!=null){
                categoryModels.clear();
                categoryModels.addAll(data);
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void setUpRecyclerView(){
        mainXml.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false));
        mainXml.recyclerView.setAdapter(adapter);
    }
    private void init(){
        searchViewModel=new ViewModelProvider(requireActivity()).get(SearchViewModel.class);
        categoryModels=new ArrayList<>();
        adapter=new CategoryAdapter(requireActivity(),categoryModels);

    }
}