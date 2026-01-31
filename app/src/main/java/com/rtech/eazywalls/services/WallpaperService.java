package com.rtech.eazywalls.services;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
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
            wallpaperManager.setBitmap(getResizedBitmap(resource),null,true,flag);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    private Bitmap getResizedBitmap(Bitmap resource) {
        DisplayMetrics displayMetrics = EazyWalls.getContext().getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // Adding padding to prevent cropping (adjust padding ratio as needed)
        Bitmap resizedBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resizedBitmap);
        float scaleX = (float) screenWidth / resource.getWidth();
        float scaleY = (float) screenHeight / resource.getHeight();
        float scale = Math.max(scaleX, scaleY); // To make sure it's scaled without losing ratio

        float dx = (screenWidth - resource.getWidth() * scale) / 2;
        float dy = (screenHeight - resource.getHeight() * scale) / 2;

        canvas.translate(dx, dy);
        canvas.scale(scale, scale);
        canvas.drawBitmap(resource, 0, 0, null);
        return resizedBitmap;

    }
}
