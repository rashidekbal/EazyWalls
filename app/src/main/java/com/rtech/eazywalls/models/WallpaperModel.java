package com.rtech.eazywalls.models;

public class WallpaperModel {
    private int id;
    private String Url;
    private boolean isFavourite;

    public WallpaperModel(int id, String url, boolean isFavourite) {
        this.id = id;
        Url = url;
        this.isFavourite = isFavourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
