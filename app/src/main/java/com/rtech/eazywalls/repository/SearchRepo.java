package com.rtech.eazywalls.repository;

import android.util.Log;

import com.rtech.eazywalls.constants.ApiEndPoints;
import com.rtech.eazywalls.interfaces.RepoDataResponse;
import com.rtech.eazywalls.interfaces.network.JsonObjectListener;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.models.SearchResult;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.networking.NetworkProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class SearchRepo {
    NetworkProvider networkProvider;
    private int WallpapersId;
    private int CategoriesId;
    public SearchRepo(){
        networkProvider=new NetworkProvider();
        this.WallpapersId=0;
        this.CategoriesId=0;
    }


    public void search(String query, int page, RepoDataResponse<SearchResult> callbackListener){
        String Url= ApiEndPoints.SEARCH_WALLPAPERS+"?target="+query+"&page="+page;
        networkProvider.get(Url, null, new JsonObjectListener() {
            @Override
            public void onSuccess(JSONObject response) {
                JSONArray data=response.optJSONArray("Data");
                assert data!=null;
                if(data.length()==2){
                    ArrayList<WallpaperModel> wallpaperModels=new ArrayList<>();
                    ArrayList<CategoryModel> categoryModels=new ArrayList<>();

                    JSONArray wallpapersResult=data.optJSONObject(0).optJSONArray("wallpapers");
                    JSONArray categoriesResult=data.optJSONObject(1).optJSONArray("categories");
                    for(int i = 0; i< Objects.requireNonNull(wallpapersResult).length(); i++){
                        JSONObject object=wallpapersResult.optJSONObject(i);
                        wallpaperModels.add(new WallpaperModel(
                                ++WallpapersId,
                                object.optString("_id"),
                                object.optString("originalUrl"),
                                object.optString("previewUrl"),
                                false
                        ));
                    }
                    for(int j = 0; j< Objects.requireNonNull(categoriesResult).length(); j++){
                        JSONObject object=categoriesResult.optJSONObject(j);
                        categoryModels.add(new CategoryModel(
                                ++CategoriesId,
                                object.optString("_id"),
                                object.optString("title"),
                                object.optString("previewUrl"),
                                0
                        ));

                    }
                    callbackListener.getData(new SearchResult(wallpaperModels,categoryModels));
                }else{
                    callbackListener.getData(new SearchResult(new ArrayList<>(),new ArrayList<>()));
                }

            }

            @Override
            public void onError(String error) {

                callbackListener.getData(new SearchResult(new ArrayList<>(),new ArrayList<>()));

            }
        });
    }

}
