package com.rtech.eazywalls;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidnetworking.AndroidNetworking;
import com.google.firebase.auth.FirebaseAuth;

public class Core {
    private static  SharedPreferences sharedPreferences;
    private static FirebaseAuth fireBaseauth;
    public static void init(Context context){
        AndroidNetworking.initialize(context);
        sharedPreferences=context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        fireBaseauth=FirebaseAuth.getInstance();
    }
    public static SharedPreferences getSharedPrefs(){
        return sharedPreferences;
    }
    public static FirebaseAuth getFireBaseauth(){
        return fireBaseauth;
    }
}
