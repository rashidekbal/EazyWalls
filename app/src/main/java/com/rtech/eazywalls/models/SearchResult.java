package com.rtech.eazywalls.models;

import java.util.ArrayList;

public class SearchResult {
   public ArrayList<WallpaperModel> wallpaperResult;
    public ArrayList<CategoryModel> categoryResult;

    public SearchResult(ArrayList<WallpaperModel> wallpaperResult, ArrayList<CategoryModel> categoryResult) {
        this.wallpaperResult = wallpaperResult;
        this.categoryResult = categoryResult;
    }

    public ArrayList<WallpaperModel> getWallpaperResult() {
        return wallpaperResult;
    }

    public ArrayList<CategoryModel> getCategoryResult() {
        return categoryResult;
    }
}
