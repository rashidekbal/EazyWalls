package com.rtech.eazywalls.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.databinding.FragmentSettingsMainBinding;

public class SettingsMainFragment extends Fragment {
    FragmentSettingsMainBinding mainXml;



    public SettingsMainFragment() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mainXml=FragmentSettingsMainBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return mainXml.getRoot();
    }
}