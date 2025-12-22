package com.rtech.eazywalls.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtech.eazywalls.models.WallpaperModel;

import java.util.ArrayList;

public class TrendingWallpaperViewModel extends AndroidViewModel {
    MutableLiveData<ArrayList<WallpaperModel>> wallpapersMutableLiveData=new MutableLiveData<>();

    public TrendingWallpaperViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<WallpaperModel>> getTrendingWallpapers(){
        if(wallpapersMutableLiveData==null||wallpapersMutableLiveData.getValue()==null){
            loadWallpapers();
        }
        return  wallpapersMutableLiveData;
    }
    private void loadWallpapers() {
        ArrayList<WallpaperModel> sampleData=new ArrayList<>();
        sampleData.add(new WallpaperModel(1,"https://img.freepik.com/free-photo/enchanted-forest-fantasy-background_23-2151910723.jpg",true));
        sampleData.add(new WallpaperModel(2,"https://img.freepik.com/free-vector/hand-painted-watercolour-tree-landscape-background_1048-18808.jpg",true));
        sampleData.add(new WallpaperModel(3,"https://img.freepik.com/free-photo/surreal-neon-tropical-flowers_23-2151665782.jpg",false));
        sampleData.add(new WallpaperModel(4,"https://img.freepik.com/free-photo/digital-art-moon-deer-wallpaper_23-2150918787.jpg",true));
        sampleData.add(new WallpaperModel(5,"https://img.freepik.com/free-photo/digital-art-moon-man-silhouette-wallpaper_23-2150918889.jpg",false));
        sampleData.add(new WallpaperModel(6,"https://img.freepik.com/free-photo/beautiful-domestic-cat-laying-fence_181624-43207.jpg",true));
        wallpapersMutableLiveData.postValue(sampleData);

    }
}
