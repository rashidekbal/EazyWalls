package com.rtech.eazywalls.models;

public class CategoryModel {
    private final int id;
    private final String _id;
    private final String title;
    private final String url;
    private final int wallpaperCount;

    public CategoryModel(int id,String _id,String title, String url,int wallpaperCount) {
        this.title = title;
        this.url = url;
        this.id=id;
        this.wallpaperCount=wallpaperCount;
        this._id=_id;
    }
    public String get_id() {
        return _id;
    }
    public int getWallpaperCount() {
        return wallpaperCount;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }

}
