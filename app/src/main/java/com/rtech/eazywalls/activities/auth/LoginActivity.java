package com.rtech.eazywalls.activities.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.rtech.eazywalls.R;
import com.rtech.eazywalls.activities.HomeActivity;
import com.rtech.eazywalls.databinding.ActivityLoginBinding;
import com.rtech.eazywalls.interfaces.auth.AuthResultCallback;
import com.rtech.eazywalls.services.AuthService;
import com.rtech.eazywalls.utils.RegexValidatorsUtil;
import com.rtech.eazywalls.utils.SharedPrefs;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding mainXml;
    AuthService authService;
    AuthResultCallback authResultCallback=new AuthResultCallback() {
        @Override
        public void success(Task<AuthResult> taskResult) {
            hideProcessing();
            if(taskResult.getResult().getUser()!=null){
                SharedPrefs.setIsLoggedIn(true);
                Intent homeIntent=new Intent(LoginActivity.this, HomeActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeIntent);
                finish();

            }else{
                Toast.makeText(LoginActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }



        }

        @Override
        public void failure(Task<AuthResult> taskResult) {
            hideProcessing();
            //TODO: handle wrong Credentials
            Toast.makeText(LoginActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(mainXml.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setClickListeners();

    }

    private void setClickListeners() {
        mainXml.signUpBtn.setOnClickListener(v-> finish());
        mainXml.forgetPasswordBtn.setOnClickListener(v-> startActivity(new Intent(this,ForgetPasswordActivity.class)));
        mainXml.loginBtn.setOnClickListener(v->handleLogin());
    }

    private void handleLogin() {
        String email=mainXml.email.getText().toString().trim();
        String password=mainXml.password.getText().toString().trim();
        if(!RegexValidatorsUtil.isValidEmail(email)){
            mainXml.email.setError("please enter a valid email");
            return;
        }
        if(password.length()<6){
            mainXml.password.setError("password must be at least 6 characters");
            return;
        }
        showProcessing();
        authService.LoginUserEmail(this,email,password,authResultCallback);

    }

    private void init(){
        authService=new AuthService();
    }
    private void hideProcessing(){
        mainXml.progressbar.setVisibility(View.GONE);
        mainXml.loginBtn.setText(R.string.register);
        mainXml.loginBtn.setEnabled(true);
    }
    private void showProcessing(){
        mainXml.progressbar.setVisibility(View.VISIBLE);
        mainXml.loginBtn.setText("");
        mainXml.loginBtn.setEnabled(false);
    }
}