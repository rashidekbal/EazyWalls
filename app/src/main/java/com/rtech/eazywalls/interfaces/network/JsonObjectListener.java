package com.rtech.eazywalls.interfaces.network;

import org.json.JSONObject;

public interface JsonObjectListener {
    void onSuccess(JSONObject response);
    void onError(String error);
}



