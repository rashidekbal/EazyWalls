package com.rtech.eazywalls.services;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rtech.eazywalls.EazyWalls;
import com.rtech.eazywalls.interfaces.WallpaperSetState;
import com.rtech.eazywalls.utils.ToastUtil;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class WallpaperService {
    public void setWallpaper(Context context, String url, WallpaperManager wallpaperManager, int flag, WallpaperSetState callback){

        Glide.with(context).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Log.d("wallpaperset", "onResourceReady: ");
                boolean isSuccess=setWallpaper(resource,wallpaperManager,flag);
                if(isSuccess){
                    callback.success();
                }else{
                    callback.error();
                }

            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
    }
    public boolean setWallpaper(Bitmap resource,WallpaperManager wallpaperManager,int flag){
        try {
            wallpaperManager.setBitmap(resource,null,true,flag);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
