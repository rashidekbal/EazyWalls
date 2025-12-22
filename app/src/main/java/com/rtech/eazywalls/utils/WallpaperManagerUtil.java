package com.rtech.eazywalls.utils;

import android.app.WallpaperManager;
import android.content.Context;

public class WallpaperManagerUtil {
    Context context;
    private final WallpaperManager wallpaperManager;
    public WallpaperManagerUtil(Context context){
        this.wallpaperManager=WallpaperManager.getInstance(context);
        this.context=context;
    }
    public WallpaperManager getInstance(){
        return wallpaperManager;
    }

}
