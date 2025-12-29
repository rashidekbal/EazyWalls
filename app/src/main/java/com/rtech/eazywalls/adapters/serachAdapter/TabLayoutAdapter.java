package com.rtech.eazywalls.adapters.serachAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rtech.eazywalls.fragments.Category_result_fragment;
import com.rtech.eazywalls.fragments.Photos_result_fragment;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    public TabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Photos_result_fragment();
        }else{
            return new Category_result_fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){return "Wallpaper";} else {
            return "Category";

        }
    }
}
