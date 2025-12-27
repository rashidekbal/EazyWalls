package com.rtech.eazywalls.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.repository.WallpaperRepo;

import java.util.ArrayList;
import java.util.Objects;

public class FeaturedWallpaperViewModel extends AndroidViewModel {
    WallpaperRepo wallpaperRepo;
    int page;
    private boolean isLast;
    private boolean loading;
    public FeaturedWallpaperViewModel(@NonNull Application application) {
        super(application);
        this.wallpaperRepo=new WallpaperRepo();
        this.page=1;
        this.loading=false;
        this.isLast=false;
    }
    MutableLiveData<ArrayList<WallpaperModel>> wallpapersMutableLiveData=new MutableLiveData<>();
    public LiveData<ArrayList<WallpaperModel>> getFeaturedWallpapers(){
        if(wallpapersMutableLiveData==null||wallpapersMutableLiveData.getValue()==null){
            loadWallpapers();
        }
        return  wallpapersMutableLiveData;
    }
    public void loadWallpapers() {
        loading=true;
        wallpaperRepo.getFeaturedWallpaper(page,data-> {
            if(data.isEmpty()){isLast=true;}
            loading=false;
            wallpapersMutableLiveData.postValue(data);
        });
    }
    public void loadMoreWallpapers(){
        if(!isLast&&!loading){
            loading=true;
            wallpaperRepo.getFeaturedWallpaper(++page,data->{
                if(data.isEmpty()){isLast=true;
                    loading=false;
                    return;
                }
                loading=false;
                ArrayList<WallpaperModel> tempArray = new ArrayList<>(Objects.requireNonNull(wallpapersMutableLiveData.getValue()));
                tempArray.addAll(data);
                wallpapersMutableLiveData.postValue(tempArray);
            });
        }

    }
}
