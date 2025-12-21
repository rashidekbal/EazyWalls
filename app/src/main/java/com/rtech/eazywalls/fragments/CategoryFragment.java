package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.databinding.FragmentCategoryBinding;


public class CategoryFragment extends Fragment {
FragmentCategoryBinding mainXml;
    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainXml=FragmentCategoryBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
}