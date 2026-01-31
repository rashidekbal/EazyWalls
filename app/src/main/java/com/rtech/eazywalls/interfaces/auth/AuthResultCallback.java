package com.rtech.eazywalls.interfaces.auth;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface AuthResultCallback {
    void success(Task<AuthResult> taskResult);
    void failure(String errorCode);
}
