package com.rtech.eazywalls.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.activities.WallpaperHandlerActivity;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.utils.CoilUtil;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<WallpaperModel> wallpaperModels;
    Context context;
    public WallpaperAdapter(Context context, ArrayList<WallpaperModel> models){
        this.wallpaperModels=models;
        this.context=context;
        setHasStableIds(true);

    }

    @Override
    public long getItemId(int position) {
        return wallpaperModels.get(position).getId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.wallpaper_card,parent,false);
        return new WallpaperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int rawPosition) {
        int position=holder.getLayoutPosition();
        bindWallpaper(position,holder);

    }

    private void bindWallpaper(int position, RecyclerView.ViewHolder viewHolder) {
        WallpaperViewHolder holder=(WallpaperViewHolder) viewHolder;
        CoilUtil.loadImage(holder.imageView,wallpaperModels.get(position).getUrl());
        if(wallpaperModels.get(position).isFavourite()){
            holder.favouriteBtn.setImageResource(R.drawable.heart_active);}
        else{
            holder.favouriteBtn.setImageResource(R.drawable.heart_icon_inactive);
        }
        holder.itemView.setOnClickListener(v->{
            Intent handlerIntent=new Intent(context, WallpaperHandlerActivity.class);
            handlerIntent.putExtra("url",wallpaperModels.get(position).getUrl());
            handlerIntent.putExtra("isFavourite",wallpaperModels.get(position).isFavourite());
            context.startActivity(handlerIntent);
        });


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

}
