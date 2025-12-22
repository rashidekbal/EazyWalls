package com.rtech.eazywalls.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.constants.FragmentId;
import com.rtech.eazywalls.databinding.ActivityHomeBinding;
import com.rtech.eazywalls.fragments.CategoryFragment;
import com.rtech.eazywalls.fragments.HomeFragment;
import com.rtech.eazywalls.viewModels.CategoryViewModel;
import com.rtech.eazywalls.viewModels.TrendingWallpaperViewModel;



public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding mainXml;
    int currentSelectedMenuItem;
    TrendingWallpaperViewModel trendingWallpaperViewModel;
    CategoryViewModel categoryViewModel;
    boolean isFirstLaunch=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFirstLaunch=false;
        mainXml=ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Window window = getWindow();
        window.getDecorView().setVisibility(View.VISIBLE);
        window.setStatusBarColor(getColor(R.color.allBlack));
        setContentView(mainXml.getRoot());
        setSupportActionBar(mainXml.toolBar);
        init();
        handlerFragmentStackChange();
        setUpActionBar();
        setUpNavigationBar();




    }
    private void init(){
        categoryViewModel=new ViewModelProvider(this).get(CategoryViewModel.class);
        trendingWallpaperViewModel= new ViewModelProvider(this).get(TrendingWallpaperViewModel.class);
        trendingWallpaperViewModel.getTrendingWallpapers();
        categoryViewModel.getCategoryLiveData();
    }
    private void handlerFragmentStackChange() {
        getSupportFragmentManager().addOnBackStackChangedListener(()->{
            if(getSupportFragmentManager().getBackStackEntryCount()==0){
                super.onBackPressed();
            }
        });
    }

    private void setUpActionBar() {
        ActionBarDrawerToggle DrawerToggle =new ActionBarDrawerToggle(HomeActivity.this,mainXml.main,mainXml.toolBar,R.string.opendrawer,R.string.closedrawer);
        mainXml.main.addDrawerListener(DrawerToggle);
        DrawerToggle.syncState();
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


    }

    private void setUpNavigationBar(){
        mainXml.navigationMenu.setOnItemSelectedListener(menuItem -> {
            final int home=R.id.home;
            final int category=R.id.category;
            final int search=R.id.search;
            final int setting=R.id.setting;
            final int menuId=menuItem.getItemId();
            if(currentSelectedMenuItem ==menuId)return true;
            if(menuId==home){
                if(getSupportFragmentManager().getBackStackEntryCount()>0){
                    getSupportFragmentManager().popBackStack(FragmentId.HOME_FRAGMENT.toString(),0);

                }else{
                    changeFragment(new HomeFragment(), FragmentId.HOME_FRAGMENT.toString());
                }
                currentSelectedMenuItem =menuId;


            }   else if(menuId==category){
                changeFragment(new CategoryFragment(), FragmentId.CATEGORY_FRAGMENT.toString());
                currentSelectedMenuItem =menuId;
            }else if(menuId==search){
                    startActivity(new Intent(HomeActivity.this,SearchActivity.class));
            }else if(menuId==setting){
                   startActivity(new Intent(HomeActivity.this,SettingsActivity.class));
                }

            return true;
        });
        mainXml.navigationMenu.setSelectedItemId(R.id.home);
    }
    private void changeFragment(Fragment fragment,@Nullable String id){
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(mainXml.fragmentHolder.getId(),fragment).addToBackStack(id).commit();
    }


}