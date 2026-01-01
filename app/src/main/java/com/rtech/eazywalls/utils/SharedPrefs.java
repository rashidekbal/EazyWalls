package com.rtech.eazywalls.utils;

import android.content.SharedPreferences;

import com.rtech.eazywalls.Core;
import com.rtech.eazywalls.constants.SharedPrefKeys;

public class SharedPrefs {
   static SharedPreferences sharedPreferences=Core.getSharedPrefs();
    public static boolean isLoggedIn(){
        return sharedPreferences.getBoolean(SharedPrefKeys.IS_LOGGED_IN.toString(),false);
    }
    public static void setIsLoggedIn(boolean state){
        sharedPreferences.edit().putBoolean(SharedPrefKeys.IS_LOGGED_IN.toString(),state).commit();
    }

}
