package com.rtech.eazywalls.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.activities.CategoryActivity;
import com.rtech.eazywalls.constants.CategoryIntentKeys;
import com.rtech.eazywalls.models.CategoryModel;
import com.rtech.eazywalls.utils.CoilUtil;
import com.rtech.eazywalls.utils.GlideUtil;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<CategoryModel> categoryModels;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
        setHasStableIds(true);
    }
    private static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleText,wallpaperCountText;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.categoryImage);
            titleText=itemView.findViewById(R.id.title);
            wallpaperCountText=itemView.findViewById(R.id.wallpaperCount);
        }
    }

    @Override
    public long getItemId(int position) {
        return categoryModels.get(position).getId();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.category_card,parent,false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int rawPosition) {
        int position=holder.getLayoutPosition();
        bindCategory(holder,position);

    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }
    private void bindCategory(RecyclerView.ViewHolder viewHolder,int position){
        CategoryViewHolder holder=(CategoryViewHolder) viewHolder;
        holder.titleText.setText(getTitle(position));
        holder.wallpaperCountText.setText(getWallpaperCount(position));
       GlideUtil.loadImage(context,holder.imageView,getImageUrl(position),R.drawable.wallpaper_placeholder);
        holder.imageView.setOnClickListener(v->{
            Intent openPage=new Intent(context, CategoryActivity.class);
            openPage.putExtra(CategoryIntentKeys._ID.toString(),get_ID(position));
            openPage.putExtra(CategoryIntentKeys.TITLE.toString(),getTitle(position));
            openPage.putExtra(CategoryIntentKeys.WALLPAPERS_COUNT.toString(),categoryModels.get(position).getWallpaperCount());
            openPage.putExtra(CategoryIntentKeys.ID.toString(),categoryModels.get(position).getId());
            openPage.putExtra(CategoryIntentKeys.URL.toString(),getImageUrl(position));
            context.startActivity(openPage);
        });

    }

    private String getTitle(int position){
        return categoryModels.get(position).getTitle();
    }
    private StringBuilder getWallpaperCount(int position){
        int itemCount=categoryModels.get(position).getWallpaperCount();
        StringBuilder string =new StringBuilder();
        string.append(itemCount);
        string.append(" wallpapers");
        return string;
    }
    private String get_ID(int position){
        return categoryModels.get(position).get_id();
    }
    private String getImageUrl(int position){
        return categoryModels.get(position).getUrl();
    }


}
