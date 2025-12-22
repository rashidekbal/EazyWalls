package com.rtech.eazywalls.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.models.WallpaperModel;

import java.util.ArrayList;

public class CategoryViewModel extends AndroidViewModel {
    public CategoryViewModel(@NonNull Application application) {
        super(application);
    }
    MutableLiveData<ArrayList<CategoryModel>> categoryMutableLiveData=new MutableLiveData<>();
    public LiveData<ArrayList<CategoryModel>> getCategoryLiveData(){
        if(categoryMutableLiveData==null||categoryMutableLiveData.getValue()==null){
            loadCategoryWallpapers();
        }
        return  categoryMutableLiveData;
    }
    private void loadCategoryWallpapers() {
        ArrayList<CategoryModel>categoryModels=new ArrayList<>();
        categoryModels.add(new CategoryModel(1,"Abstract","https://img.freepik.com/free-photo/enchanted-forest-fantasy-background_23-2151910723.jpg",640));
        categoryModels.add(new CategoryModel(2,"Architect","https://img.freepik.com/free-vector/hand-painted-watercolour-tree-landscape-background_1048-18808.jpg",480));
        categoryModels.add(new CategoryModel(3,"ColorFull","https://img.freepik.com/free-photo/surreal-neon-tropical-flowers_23-2151665782.jpg",600));
        categoryModels.add(new CategoryModel(4,"Nature","https://img.freepik.com/free-photo/digital-art-moon-deer-wallpaper_23-2150918787.jpg",225));
        categoryModels.add(new CategoryModel(5,"Galaxy","https://img.freepik.com/free-photo/digital-art-moon-man-silhouette-wallpaper_23-2150918889.jpg",442));
        categoryModels.add(new CategoryModel(6,"Mountains","https://img.freepik.com/free-photo/beautiful-domestic-cat-laying-fence_181624-43207.jpg",225));
        categoryMutableLiveData.postValue(categoryModels);

    }
}
