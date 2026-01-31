package com.rtech.eazywalls.services;


import android.app.Activity;
import android.content.Context;
import android.credentials.GetCredentialRequest;
import android.os.Bundle;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.firebase.auth.FirebaseAuthException;
import com.rtech.eazywalls.Core;
import com.rtech.eazywalls.R;
import com.rtech.eazywalls.interfaces.auth.AuthResultCallback;

public class AuthService {
    public void createNewUser(Activity activity, String email, String password, AuthResultCallback callback){
        Core.getFireBaseauth().createUserWithEmailAndPassword(email,password).addOnCompleteListener(activity, task->{
            if(task.isSuccessful()){
                callback.success(task);


            }else{
                Exception e=task.getException();
                if(e instanceof FirebaseAuthException){
                    String errorCode=((FirebaseAuthException)e).getErrorCode();
                    callback.failure(errorCode);

                }
            }

        });
    }
    public void LoginUserEmail(Activity activity,String email,String password,AuthResultCallback callback){
        Core.getFireBaseauth().signInWithEmailAndPassword(email,password).addOnCompleteListener(activity,task->{
            if(task.isSuccessful()){
                callback.success(task);
            }else{
                Exception e=task.getException();
                if(e instanceof FirebaseAuthException){
                    String errorCode=((FirebaseAuthException)e).getErrorCode();
                    callback.failure(errorCode);

                }
            }

        });
    }
    public void LoginGoogle(Context context){
           }
    public void LoginFacebook(){}



}
