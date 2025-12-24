package com.rtech.eazywalls.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rtech.eazywalls.R;
import com.rtech.eazywalls.activities.PreviewActivity;
import com.rtech.eazywalls.constants.WallpaperListType;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.services.DownloadService;
import com.rtech.eazywalls.utils.CoilUtil;
import com.rtech.eazywalls.utils.DownloadManagerUtil;
import com.rtech.eazywalls.utils.GlideUtil;
import com.rtech.eazywalls.utils.PageNavigatorUtil;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<WallpaperModel> wallpaperModels;
    Context context;
    String wallpaperListType;
    DownloadManagerUtil downloadManagerUtil;
    DownloadService downloadService;
    public WallpaperAdapter(Context context, ArrayList<WallpaperModel> models,String ListType){
        this.wallpaperModels=models;
        this.context=context;
        setHasStableIds(true);
        this.wallpaperListType=ListType;
        this.downloadService=new DownloadService();
        this.downloadManagerUtil=new DownloadManagerUtil(context);

    }



    @Override
    public long getItemId(int position) {
        return wallpaperModels.get(position).getId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(wallpaperListType.equals(WallpaperListType.TRENDING.toString())){
            v= LayoutInflater.from(context).inflate(R.layout.wallpaper_card,parent,false);
            return new WallpaperViewHolder(v);
        }else{
            v=LayoutInflater.from(context).inflate(R.layout.wallpaper_card_v2,parent,false);
            return new WallpaperViewHolderV2(v);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int rawPosition) {
        int position=holder.getLayoutPosition();
        if(holder instanceof WallpaperViewHolder){
            bindWallpaperV1(position,holder);
        }else{
            bindWallpaperV2(position,holder);
        }


    }

    private void bindWallpaperV2(int position, RecyclerView.ViewHolder viewHolder) {
        WallpaperViewHolderV2 holder=(WallpaperViewHolderV2) viewHolder;
        GlideUtil.loadImage(context,holder.imageView,wallpaperModels.get(position).getPreviewUrl(),R.drawable.wallpaper_placeholder);
        if(wallpaperModels.get(position).isFavourite()){
            holder.favouriteBtn.setImageResource(R.drawable.heart_active);}
        else{
            holder.favouriteBtn.setImageResource(R.drawable.heart_icon_inactive);
        }
        holder.itemView.setOnClickListener(v->{
            openPreviewPage(position);
        });
        holder.downloadBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(context).setTitle("Download").setMessage("Do you want to download this wallpaper").setCancelable(true)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                        })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            downloadService.DownloadWallpaper(context,downloadManagerUtil.getInstance(),wallpaperModels.get(position).getUrl());
                            dialog.dismiss();

                        }
                    }).show();

        });

    }

    private void bindWallpaperV1(int position, RecyclerView.ViewHolder viewHolder) {
        WallpaperViewHolder holder=(WallpaperViewHolder) viewHolder;
        GlideUtil.loadImage(context,holder.imageView,wallpaperModels.get(position).getPreviewUrl(),R.drawable.wallpaper_placeholder);
        if(wallpaperModels.get(position).isFavourite()){
            holder.favouriteBtn.setImageResource(R.drawable.heart_active);}
        else{
            holder.favouriteBtn.setImageResource(R.drawable.heart_icon_inactive);
        }
        holder.itemView.setOnClickListener(v->{
           openPreviewPage(position);
        });


    }
    private void openPreviewPage(int position){
        PageNavigatorUtil.openPreviewPage(context,wallpaperModels.get(position));
    }

    @Override
    public int getItemCount() {
        return wallpaperModels.size();
    }
    private static class WallpaperViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView favouriteBtn;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.wallpaper_imageView);
            favouriteBtn=itemView.findViewById(R.id.favouriteBtn);
        }
    }
    private static class  WallpaperViewHolderV2 extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView favouriteBtn;
        ImageView downloadBtn;
        public WallpaperViewHolderV2(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.wallpaper_imageView);
            favouriteBtn=itemView.findViewById(R.id.favouriteBtn);
            downloadBtn=itemView.findViewById(R.id.downloadBtn);
        }
    }

}
