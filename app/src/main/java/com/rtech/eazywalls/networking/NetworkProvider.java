package com.rtech.eazywalls.networking;

import android.util.Log;

import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.rtech.eazywalls.interfaces.network.JsonObjectListener;

import org.json.JSONObject;

public class NetworkProvider {
    public void get(String url, @Nullable String token, JsonObjectListener callbackListener){
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
//                        Log.d("network_error", "dataloaded: "+jsonObject.toString());
                        callbackListener.onSuccess(jsonObject);
                    }

                    @Override
                    public void onError(ANError anError) {
//                        Log.d("network_error", "onError: "+anError.getErrorBody());
                        callbackListener.onError(anError.toString());
                    }
                });
    }

    public void post(String url, JSONObject packet,@Nullable String token,JsonObjectListener callbackListener){
        AndroidNetworking.post(url)
                .addJSONObjectBody(packet)
                .setPriority(Priority.HIGH)
                .build().getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        callbackListener.onSuccess(jsonObject);
                    }

                    @Override
                    public void onError(ANError anError) {
                        callbackListener.onError(anError.toString());

                    }
                });

    }

}
