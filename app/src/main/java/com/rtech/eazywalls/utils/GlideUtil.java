package com.rtech.eazywalls.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideUtil {
    public static void loadImage(Context context, ImageView imageView, String url, int placeholder){
        Glide.with(context).load(url).placeholder(placeholder).thumbnail(0.1f).into(imageView);
    }
}
