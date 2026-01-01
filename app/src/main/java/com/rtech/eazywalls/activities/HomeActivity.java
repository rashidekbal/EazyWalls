package com.rtech.eazywalls.activities;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.rtech.eazywalls.Core;
import com.rtech.eazywalls.R;
import com.rtech.eazywalls.activities.auth.SignUpActivity;
import com.rtech.eazywalls.databinding.SidebarLayoutBinding;
import com.rtech.eazywalls.fragments.CategoryFragment;
import com.rtech.eazywalls.fragments.HomeFragment;
import com.rtech.eazywalls.fragments.SettingsMainFragment;
import com.rtech.eazywalls.constants.FragmentId;
import com.rtech.eazywalls.databinding.ActivityHomeBinding;
import com.rtech.eazywalls.utils.SharedPrefs;
import com.rtech.eazywalls.viewModels.CategoryViewModel;
import com.rtech.eazywalls.viewModels.TrendingWallpaperViewModel;


@SuppressWarnings("deprecation")
public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding mainXml;
    SidebarLayoutBinding sidebarLayoutBinding;
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
        handleLoginState();
        handlerFragmentStackChange();
        setUpActionBar();
        setUpNavigationBar();
        handleClickListeners();
        setUpSideBarNavigation();

    }

    private void handleLoginState() {

        if(Core.getFireBaseauth().getCurrentUser()!=null){
            sidebarLayoutBinding.groupLoggedOut.setVisibility(View.GONE);
            sidebarLayoutBinding.groupLoggedIn.setVisibility(View.VISIBLE);

        }else{
            sidebarLayoutBinding.groupLoggedIn.setVisibility(View.GONE);
            sidebarLayoutBinding.groupLoggedOut.setVisibility(View.VISIBLE);
        }

    }

    private void setUpSideBarNavigation() {
        mainXml.sideBar.setNavigationItemSelectedListener(menuItem -> {
            Toast.makeText(HomeActivity.this, "selected"+menuItem, Toast.LENGTH_SHORT).show();
            mainXml.main.closeDrawers();
            return true;
        });
    }

    private void handleClickListeners() {
        sidebarLayoutBinding.btnLoginSidebar.setOnClickListener(v->{
            mainXml.main.closeDrawers();
            startActivity(new Intent(HomeActivity.this, SignUpActivity.class));
        });
        sidebarLayoutBinding.logOutButton.setOnClickListener(v->{
            Core.getSharedPrefs().edit().clear().commit();
            Core.getFireBaseauth().signOut();
            handleLoginState();
        });

    }


    //TODO: implement new back press callback api
    @Override
    public void onBackPressed() {
        int count= getSupportFragmentManager().getBackStackEntryCount();
        if(count==1){
            finish();
        }else{

            super.onBackPressed();
        }
    }

    private void init(){
        categoryViewModel=new ViewModelProvider(this).get(CategoryViewModel.class);
        trendingWallpaperViewModel= new ViewModelProvider(this).get(TrendingWallpaperViewModel.class);
        trendingWallpaperViewModel.getTrendingWallpapers();
        categoryViewModel.getCategoryLiveData();
        sidebarLayoutBinding=SidebarLayoutBinding.bind(mainXml.sideBar.getHeaderView(0));


    }
    private void handlerFragmentStackChange() {
        getSupportFragmentManager().addOnBackStackChangedListener(()->{

            int entryCount=getSupportFragmentManager().getBackStackEntryCount();
            String entryId=getSupportFragmentManager().getBackStackEntryAt(entryCount-1).getName();
            if(entryId!=null){
                if(!entryId.equals(FragmentId.SETTINGS_MAIN_FRAGMENT.toString())){
                    mainXml.navigationMenu.setVisibility(View.VISIBLE);
                }
                if(entryId.equals(FragmentId.HOME_FRAGMENT.toString())){
                    mainXml.navigationMenu.setSelectedItemId(R.id.home);
                    currentSelectedMenuItem=R.id.home;
                }else if (entryId.equals(FragmentId.CATEGORY_FRAGMENT.toString())){
                    mainXml.navigationMenu.setSelectedItemId(R.id.category);
                    currentSelectedMenuItem=R.id.category;
                }
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
                    startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                    return false;
            }else if(menuId==setting){

                   mainXml.navigationMenu.setVisibility(View.GONE);
                   changeFragment(new SettingsMainFragment(),FragmentId.SETTINGS_MAIN_FRAGMENT.toString());
                   currentSelectedMenuItem=menuId;
                }

            return true;
        });
        mainXml.navigationMenu.setSelectedItemId(R.id.home);
    }
    private void changeFragment(Fragment fragment,@Nullable String id){
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(mainXml.fragmentHolder.getId(),fragment).addToBackStack(id).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleLoginState();
    }
}