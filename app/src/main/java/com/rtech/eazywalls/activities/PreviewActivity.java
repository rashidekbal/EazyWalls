package com.rtech.eazywalls.activities;

import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rtech.eazywalls.R;
import com.rtech.eazywalls.databinding.ActivityPreviewBinding;
import com.rtech.eazywalls.interfaces.WallpaperSetState;
import com.rtech.eazywalls.models.WallpaperModel;
import com.rtech.eazywalls.services.DownloadService;
import com.rtech.eazywalls.services.WallpaperService;
import com.rtech.eazywalls.utils.CoilUtil;
import com.rtech.eazywalls.utils.DownloadManagerUtil;
import com.rtech.eazywalls.utils.GlideUtil;
import com.rtech.eazywalls.utils.ToastUtil;
import com.rtech.eazywalls.utils.WallpaperManagerUtil;

public class PreviewActivity extends AppCompatActivity {
    ActivityPreviewBinding mainXml;
    WallpaperModel wallpaperModel;
    DownloadManagerUtil downloadManagerUtil;
    WallpaperManagerUtil wallpaperManagerUtil;
    WallpaperService wallpaperService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivityPreviewBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(mainXml.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, 0);
            return insets;
        });
        Window window=getWindow();
        window.getDecorView().setVisibility(View.VISIBLE);
        window.setStatusBarColor(getColor(R.color.allBlack));
        init();
        setUpData();
        setClickHandler();

    }
// ----------------------------- method for handling ui elements click --------------------   //
    private void setClickHandler() {
        mainXml.applyCardBtnView.setOnClickListener(v-> showWallpaperApplyDialog());
        mainXml.downloadBtn.setOnClickListener(v-> new AlertDialog.Builder(this).setTitle("Download").setMessage("Do you want to download this wallpaper").setCancelable(true).setNegativeButton("No", (dialog, which) -> dialog.dismiss()).setPositiveButton("Yes", (dialog, which) -> {
            new DownloadService().DownloadWallpaper(PreviewActivity.this,downloadManagerUtil.getInstance(),wallpaperModel.getUrl());
            dialog.dismiss();

        }).show());
        mainXml.backBtn.setOnClickListener(v->finish());

    }
// ------------------------------------ method for settingUp  preview page ------------------------------------- //
    private void setUpData() {
        GlideUtil.loadImage(this,mainXml.previewImage,wallpaperModel.getUrl(),R.drawable.wallpaper_placeholder);
    }
// -------------------------------------------- function to init data and objects ----------------------------------//
    private void init(){
        wallpaperManagerUtil=new WallpaperManagerUtil(this);
        downloadManagerUtil=new DownloadManagerUtil(this);
        wallpaperService=new WallpaperService();
        Intent data=getIntent();
        if(data!=null){
            wallpaperModel=new WallpaperModel(data.getIntExtra("id",0),data.getStringExtra("_id"),data.getStringExtra("url"),data.getStringExtra("previewUrl"),data.getBooleanExtra("isFavourite",false));
        }
        else{
            Toast.makeText(this,"error occured..",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
// --------------------------- show option sheet dialog for applying wallpaper -------------------------------- //
    private void showWallpaperApplyDialog(){
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this,R.style.TransparentBottomSheet);
        bottomSheetDialog.setContentView(R.layout.wallpaper_apply_bottomsheet);
        bottomSheetDialog.setCancelable(true);
        set_BottomSheet_Dialog_option_Handler(bottomSheetDialog);
        bottomSheetDialog.show();
    }
// -------------------------------------- bottom sheet dialog option click listener for setting wallpaper ------------------------- //
    private void set_BottomSheet_Dialog_option_Handler(BottomSheetDialog dialog) {
        TextView setLockScreen,setHomeScreen,setBoth,reportBtn;
        setLockScreen=dialog.findViewById(R.id.set_lock_screen_btn);
        setHomeScreen=dialog.findViewById(R.id.set_home_screen_btn);
        setBoth=dialog.findViewById(R.id.set_both_btn);
        reportBtn=dialog.findViewById(R.id.report_btn);
        if(setLockScreen!=null){
            setLockScreen.setOnClickListener(v->{
                setWallpaperSettingState(dialog);
           setWallpaper(WallpaperManager.FLAG_LOCK);
            });
        }
        if(setHomeScreen!=null){
            setHomeScreen.setOnClickListener(v->{
                setWallpaperSettingState(dialog);
               setWallpaper(WallpaperManager.FLAG_SYSTEM);
            });
        }
        if(setBoth!=null){
            setBoth.setOnClickListener(v->{
                setWallpaperSettingState(dialog);
              setWallpaper(WallpaperManager.FLAG_SYSTEM|WallpaperManager.FLAG_LOCK);
            });
        }
        if(reportBtn!=null){
            reportBtn.setOnClickListener(v-> ToastUtil.showToast(PreviewActivity.this,"feature yet to be added... "));
        }


    }
// ---------------------------------------- method for setting wallpaper ---------------------------------- //
 private void setWallpaper(int flag){
        wallpaperService.setWallpaper(this, wallpaperModel.getUrl(), wallpaperManagerUtil.getInstance(), flag, new WallpaperSetState() {
           @Override
           public void success() {
               show_Wallpaper_Set_Toast(true);

           }

           @Override
           public void error() {
               show_Wallpaper_Set_Toast(false);

           }
       });
    }
//    ----------------------------------- operation after setting wallpaper ----------------------------//
    private void show_Wallpaper_Set_Toast(Boolean isSuccess){
        mainXml.progressbar.setVisibility(View.GONE);
        mainXml.applyCardBtnView.setEnabled(true);
        if(isSuccess){
            ToastUtil.showToast(this,"wallpaper set success...");
            return ;

        }
        ToastUtil.showToast(this,"wallpaper set failed...");

    }
//    ----------------------------      operation while setting wallpaper ---------------------- //
    private void setWallpaperSettingState(BottomSheetDialog dialog){
        mainXml.applyCardBtnView.setEnabled(false);
        dialog.hide();
        mainXml.progressbar.setVisibility(View.VISIBLE);


    }
}