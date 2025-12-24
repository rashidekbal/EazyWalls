package com.rtech.eazywalls.repository;

import com.google.gson.JsonObject;
import com.rtech.eazywalls.constants.ApiEndPoints;
import com.rtech.eazywalls.interfaces.RepoDataResponse;
import com.rtech.eazywalls.interfaces.network.JsonObjectListener;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.networking.NetworkProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryRepo {
    NetworkProvider networkProvider;
    public CategoryRepo(){
        networkProvider=new NetworkProvider();
    }
    public void  getCategories(RepoDataResponse<ArrayList<CategoryModel>> callbackListener){
        networkProvider.get(ApiEndPoints.GET_CATEGORY, null,new JsonObjectListener() {
            @Override
            public void onSuccess(JSONObject response) {
                int id=0;
                JSONArray Data=response.optJSONArray("Data");
                ArrayList<CategoryModel> categoryModels=new ArrayList<>();
                for(int i = 0; i< (Data != null ? Data.length() : 0); i++){
                    JSONObject object=Data.optJSONObject(i);
                    CategoryModel categoryModel=new CategoryModel(id++,object.optString("_id"),object.optString("title"),object.optString("previewUrl"),object.optInt("wallpaperCount"));
                    categoryModels.add(categoryModel);
                }
                callbackListener.getData(categoryModels);
            }

            @Override
            public void onError(String error) {
                callbackListener.getData(new ArrayList<>());
            //TODO: handle the error
            }
        });

    }

}
