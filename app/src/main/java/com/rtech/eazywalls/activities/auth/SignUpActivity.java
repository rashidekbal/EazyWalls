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
import com.google.firebase.auth.FirebaseAuth;
import com.rtech.eazywalls.R;
import com.rtech.eazywalls.databinding.ActivitySignUpBinding;
import com.rtech.eazywalls.interfaces.auth.AuthResultCallback;
import com.rtech.eazywalls.services.AuthService;
import com.rtech.eazywalls.utils.RegexValidatorsUtil;
import com.rtech.eazywalls.utils.SharedPrefs;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding mainXml;
    AuthService authService;
    FirebaseAuth firebaseAuth;
    AuthResultCallback authResultCallback=new AuthResultCallback() {
        @Override
        public void success(Task<AuthResult> taskResult) {
hideProcessing();
            if(taskResult.getResult().getUser()!=null){
                SharedPrefs.setIsLoggedIn(true);
                taskResult.getResult().getUser().sendEmailVerification();
                Toast.makeText(SignUpActivity.this, "account created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }else{

                Toast.makeText(SignUpActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                finish();
            }

            }


        @Override
        public void failure(Task<AuthResult> taskResult) {
            //TODO: add error handling
            hideProcessing();
            Toast.makeText(SignUpActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivitySignUpBinding.inflate(getLayoutInflater());
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
    private void init(){
        authService=new AuthService();
        firebaseAuth=FirebaseAuth.getInstance();
    }
    private void setClickListeners(){
        mainXml.registerButton.setOnClickListener(v->{
           handleSignUp();
        });
        mainXml.loginBtn.setOnClickListener(v-> startActivity(new Intent(this,LoginActivity.class)));
        mainXml.backBtn.setOnClickListener(v->finish());
    }

    private void handleSignUp() {
        String email=mainXml.email.getText().toString().trim();
        String password=mainXml.password.getText().toString().trim();
        String confirmPassword=mainXml.confirmPassword.getText().toString().trim();
        if(!RegexValidatorsUtil.isValidEmail(email)){mainXml.email.setError("please enter a valid email");return;}
        if(password.length()<6){mainXml.password.setError("password must be at least 6 characters");return;}
        if(!password.equals(confirmPassword)){mainXml.confirmPassword.setError("passwords do not match");return;}
        showProcessing();
        authService.createNewUser(this,email,password,authResultCallback);

    }

    private void hideProcessing(){
        mainXml.progressbar.setVisibility(View.GONE);
        mainXml.registerButton.setText(R.string.register);
        mainXml.registerButton.setEnabled(true);
    }
    private void showProcessing(){
        mainXml.progressbar.setVisibility(View.VISIBLE);
        mainXml.registerButton.setText("");
        mainXml.registerButton.setEnabled(false);
    }

}