package com.rtech.eazywalls.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.adapters.serachAdapter.TabLayoutAdapter;
import com.rtech.eazywalls.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding mainXml;
    TabLayoutAdapter tabLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainXml=ActivitySearchBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(mainXml.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        setUpTabLayout();
        setUpClickHandler();
    }

    private void setUpClickHandler() {
        mainXml.closeBtn.setOnClickListener(v->finish());
    }

    private void setUpTabLayout() {
        mainXml.viewPager.setAdapter(tabLayoutAdapter);
        mainXml.tabLayout.setupWithViewPager(mainXml.viewPager);
        mainXml.tabLayout.setTabRippleColor(null);

    }

    private void init(){
        tabLayoutAdapter=new TabLayoutAdapter(getSupportFragmentManager());
    }
}