package com.rtech.eazywalls.services;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.rtech.eazywalls.utils.ToastUtil;

public class DownloadService {
    public void DownloadWallpaper(Context context, DownloadManager downloadManager,String url){
        Uri uri=Uri.parse(url);
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setTitle("Downloading");
        request.setDescription("downloading wallpaper");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,uri.getLastPathSegment());
        downloadManager.enqueue(request);
        ToastUtil.showToast(context,"downloading wallpaper..");
    }
}
