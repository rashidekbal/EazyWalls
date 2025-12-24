package com.rtech.eazywalls.models;

public class WallpaperModel {
    private final int id;
    private final String _id;
    private final String Url;
    private final String previewUrl;
    private boolean isFavourite;

    public WallpaperModel(int id,String _id ,String url,String previewUrl, boolean isFavourite) {
        this.id = id;
        Url = url;
        this.isFavourite = isFavourite;
        this._id=_id;
        this.previewUrl=previewUrl;
    }

    public int getId() {
        return id;
    }
    public String getUrl() {
        return Url;
    }
    public boolean isFavourite() {
        return isFavourite;
    }
    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
    public String get_id() {
        return _id;
    }
    public String getPreviewUrl() {
        return previewUrl;
    }
}
