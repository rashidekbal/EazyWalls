package com.rtech.eazywalls;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;

public class Core {
    public static void init(Context context){
        AndroidNetworking.initialize(context);
    }
}
