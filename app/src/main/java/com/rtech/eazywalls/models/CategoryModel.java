package com.rtech.eazywalls.models;

public class CategoryModel {
    private final int id;
    private String title;
    private String url;
    private final int wallpaperCount;

    public CategoryModel(int id,String title, String url,int wallpaperCount) {
        this.title = title;
        this.url = url;
        this.id=id;
        this.wallpaperCount=wallpaperCount;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
