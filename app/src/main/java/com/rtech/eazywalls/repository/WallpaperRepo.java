package com.rtech.eazywalls.repository;

import com.rtech.eazywalls.constants.ApiEndPoints;
import com.rtech.eazywalls.interfaces.RepoDataResponse;
import com.rtech.eazywalls.interfaces.network.JsonObjectListener;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.networking.NetworkProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WallpaperRepo {
    //TODO:make the WallpaperId centralized to prevent duplicate ids on reload of data
    NetworkProvider networkProvider;
    int WallpaperId;
    public WallpaperRepo(){
        networkProvider=new NetworkProvider();
        this.WallpaperId=0;
    }
    public void getWallpaperOfCategory(String category, int page, RepoDataResponse<ArrayList<WallpaperModel>> callback){
        String Url= ApiEndPoints.GET_WALLPAPER+"?category="+category+"&page="+page;
        networkProvider.get(Url, null, new JsonObjectListener() {
            @Override
            public void onSuccess(JSONObject response) {
                JSONArray Data=response.optJSONArray("Data");
                ArrayList<WallpaperModel> wallpaperModels=new ArrayList<>();
                for(int i = 0; i< (Data != null ? Data.length() : 0); i++){
                    JSONObject object=Data.optJSONObject(i);
                    WallpaperModel wallpaperModel=new WallpaperModel(
                            ++WallpaperId,object.optString("_id"),
                            object.optString("originalUrl"),
                            object.optString("previewUrl"),
                            false
//                            TODO: add favourite feature
//                            object.optBoolean("isFavourite")

                    );
                    wallpaperModels.add(wallpaperModel);
                }
                callback.getData(wallpaperModels);

            }

            @Override
            public void onError(String error) {
                callback.getData(new ArrayList<>());

            }
        });
    }
    public void getTrendingWallpaper( int page, RepoDataResponse<ArrayList<WallpaperModel>> callback){
        String Url= ApiEndPoints.GET_WALLPAPER+"?type=trending&page="+page;
        networkProvider.get(Url, null, new JsonObjectListener() {
            @Override
            public void onSuccess(JSONObject response) {
                JSONArray Data=response.optJSONArray("Data");
                ArrayList<WallpaperModel> wallpaperModels=new ArrayList<>();
                for(int i = 0; i< (Data != null ? Data.length() : 0); i++){
                    JSONObject object=Data.optJSONObject(i);
                    WallpaperModel wallpaperModel=new WallpaperModel(
                            ++WallpaperId,object.optString("_id"),
                            object.optString("originalUrl"),
                            object.optString("previewUrl"),
                            false
//                            TODO: add favourite feature
//                            object.optBoolean("isFavourite")

                    );
                    wallpaperModels.add(wallpaperModel);
                }
                callback.getData(wallpaperModels);

            }

            @Override
            public void onError(String error) {
                callback.getData(new ArrayList<>());

            }
        });
    }
    public void getFeaturedWallpaper( int page, RepoDataResponse<ArrayList<WallpaperModel>> callback){
        String Url= ApiEndPoints.GET_WALLPAPER+"?type=featured&page="+page;
        networkProvider.get(Url, null, new JsonObjectListener() {
            @Override
            public void onSuccess(JSONObject response) {

                JSONArray Data=response.optJSONArray("Data");
                ArrayList<WallpaperModel> wallpaperModels=new ArrayList<>();
                for(int i = 0; i< (Data != null ? Data.length() : 0); i++){
                    JSONObject object=Data.optJSONObject(i);
                    WallpaperModel wallpaperModel=new WallpaperModel(
                            ++WallpaperId,object.optString("_id"),
                            object.optString("originalUrl"),
                            object.optString("previewUrl"),
                            false
//                            TODO: add favourite feature
//                            object.optBoolean("isFavourite")

                    );
                    wallpaperModels.add(wallpaperModel);
                }
                callback.getData(wallpaperModels);

            }

            @Override
            public void onError(String error) {
                callback.getData(new ArrayList<>());

            }
        });
    }
}
