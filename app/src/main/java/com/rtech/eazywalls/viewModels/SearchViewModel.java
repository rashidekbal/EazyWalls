package com.rtech.eazywalls.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.repository.SearchRepo;

import java.util.ArrayList;
import java.util.Objects;

public class SearchViewModel extends AndroidViewModel {
    public SearchViewModel(@NonNull Application application) {
        super(application);
    }
    int page=1;
    String target;
    boolean isLastWallpaperResult =false;
    boolean isLastCategoryResult=false;
    boolean isLoading=false;
    SearchRepo searchRepo=new SearchRepo();
    MutableLiveData<ArrayList<WallpaperModel>> WallpaperResult=new MutableLiveData<>();
    MutableLiveData<ArrayList<CategoryModel>> CategoryResult=new MutableLiveData<>();
    public void search(String target){
        isLoading=true;
        page=1;
        this.target=target;
        searchRepo.search(target,page,data->{
            isLoading=false;
            if(data.categoryResult.isEmpty())isLastCategoryResult=true;
            if(data.wallpaperResult.isEmpty())isLastWallpaperResult=true;
            WallpaperResult.postValue(data.getWallpaperResult());
            CategoryResult.postValue(data.getCategoryResult());
        });
    }
    public LiveData<ArrayList<WallpaperModel>> getWallpaperResult(){
        return WallpaperResult;
    }
    public LiveData<ArrayList<CategoryModel>> getCategoryResult(){
        return CategoryResult;
    }

    public void loadMoreWallpaperResult(){
        if(!isLoading&&!isLastWallpaperResult){
            isLoading=true;
            searchRepo.search(target,++page,data -> {
                isLoading=false;
                if(data.categoryResult.isEmpty())isLastCategoryResult=true;
                if(data.wallpaperResult.isEmpty())isLastWallpaperResult=true;
                ArrayList<WallpaperModel> temp = new ArrayList<>(Objects.requireNonNull(WallpaperResult.getValue()));
                temp.addAll(data.getWallpaperResult());
                WallpaperResult.postValue(temp);
                ArrayList<CategoryModel> tempCat = new ArrayList<>(Objects.requireNonNull(CategoryResult.getValue()));
                tempCat.addAll(data.getCategoryResult());
                CategoryResult.postValue(tempCat);
            });

        }
    }
    public  void loadMoreCategoryResult(){
        if(!isLoading&&!isLastCategoryResult){
            isLoading=true;
            searchRepo.search(target,++page,data -> {
                isLoading=false;
                if(data.categoryResult.isEmpty())isLastCategoryResult=true;
                if(data.wallpaperResult.isEmpty())isLastWallpaperResult=true;
                ArrayList<WallpaperModel> temp = new ArrayList<>(Objects.requireNonNull(WallpaperResult.getValue()));
                temp.addAll(data.getWallpaperResult());
                WallpaperResult.postValue(temp);
                ArrayList<CategoryModel> tempCat = new ArrayList<>(Objects.requireNonNull(CategoryResult.getValue()));
                tempCat.addAll(data.getCategoryResult());
                CategoryResult.postValue(tempCat);
            });

        }
    }


}
