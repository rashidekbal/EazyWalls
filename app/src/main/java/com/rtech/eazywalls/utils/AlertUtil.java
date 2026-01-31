package com.rtech.eazywalls.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertUtil {
    public static void showError(Context context, String title, String message){

        new AlertDialog.Builder(context).setTitle(title).setMessage(message).setCancelable(true).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        }).show();
    }
}
