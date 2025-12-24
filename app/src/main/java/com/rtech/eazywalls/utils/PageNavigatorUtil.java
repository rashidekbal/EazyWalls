package com.rtech.eazywalls.utils;

import android.content.Context;
import android.content.Intent;

import com.rtech.eazywalls.activities.PreviewActivity;
import com.rtech.eazywalls.models.WallpaperModel;

public class PageNavigatorUtil {
    public static void openPreviewPage(Context context, WallpaperModel model){
        Intent handlerIntent=new Intent(context, PreviewActivity.class);
        handlerIntent.putExtra("id",model.getId());
        handlerIntent.putExtra("_id",model.get_id());
        handlerIntent.putExtra("url",model.getUrl());
        handlerIntent.putExtra("previewUrl",model.getPreviewUrl());
        handlerIntent.putExtra("isFavourite",model.isFavourite());
        context.startActivity(handlerIntent);
    }
}
