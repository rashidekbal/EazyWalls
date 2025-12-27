package com.rtech.eazywalls.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.repository.CategoryRepo;

import java.util.ArrayList;

public class CategoryViewModel extends AndroidViewModel {
    CategoryRepo categoryRepo;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepo=new CategoryRepo();
    }
    MutableLiveData<ArrayList<CategoryModel>> categoryMutableLiveData=new MutableLiveData<>();
    public LiveData<ArrayList<CategoryModel>> getCategoryLiveData(){
        if(categoryMutableLiveData==null||categoryMutableLiveData.getValue()==null){
            loadCategoryWallpapers();
        }
        return  categoryMutableLiveData;
    }
    public void loadCategoryWallpapers() {
        categoryRepo.getCategories(data->  categoryMutableLiveData.postValue(data));
      ;

    }
}
