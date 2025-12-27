package com.rtech.eazywalls.utils;

import android.app.DownloadManager;
import android.content.Context;

public class DownloadManagerUtil {
    private Context context;
    private final DownloadManager downloadManager;
    public DownloadManagerUtil(Context context){
        downloadManager=(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

    }
    public DownloadManager getInstance(){
        return downloadManager;
    }


}
