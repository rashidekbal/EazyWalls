package com.rtech.eazywalls.services;


import android.app.Activity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rtech.eazywalls.Core;
import com.rtech.eazywalls.interfaces.auth.AuthResultCallback;
import com.rtech.eazywalls.utils.SharedPrefs;

public class AuthService {
    public void createNewUser(Activity activity, String email, String password, AuthResultCallback callback){
        Core.getFireBaseauth().createUserWithEmailAndPassword(email,password).addOnCompleteListener(activity, task->{
            if(task.isSuccessful()){
                callback.success(task);


            }else{
                callback.failure(task);
            }

        });
    }
    public void LoginUserEmail(Activity activity,String email,String password,AuthResultCallback callback){
        Core.getFireBaseauth().signInWithEmailAndPassword(email,password).addOnCompleteListener(activity,task->{
            if(task.isSuccessful()){
                callback.success(task);
            }else{
                callback.failure(task);
            }

        });
    }
    public void LoginGoogle(){}
    public void LoginFacebook(){}



}
