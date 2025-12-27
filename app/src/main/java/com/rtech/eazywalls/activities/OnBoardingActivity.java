package com.rtech.eazywalls.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.databinding.ActivityOnBoardingBinding;

public class OnBoardingActivity extends AppCompatActivity {
    ActivityOnBoardingBinding mainXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivityOnBoardingBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(mainXml.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}