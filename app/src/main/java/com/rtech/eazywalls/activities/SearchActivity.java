package com.rtech.eazywalls.activities;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.rtech.eazywalls.R;
import com.rtech.eazywalls.adapters.serachAdapter.TabLayoutAdapter;
import com.rtech.eazywalls.databinding.ActivitySearchBinding;
import com.rtech.eazywalls.viewModels.SearchViewModel;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding mainXml;
    TabLayoutAdapter tabLayoutAdapter;
    SearchViewModel searchViewModel;

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
        mainXml.searchIcon.setOnClickListener(v->{
            String query=mainXml.searchFiled.getText().toString().trim();
            if(query.isEmpty()){
                return;
            }
            mainXml.searchFiled.setText("");
            Toast.makeText(this, "searching", Toast.LENGTH_SHORT).show();
            InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            searchViewModel.search(query);
        });
    }

    private void setUpTabLayout() {
        mainXml.viewPager.setAdapter(tabLayoutAdapter);
        mainXml.tabLayout.setupWithViewPager(mainXml.viewPager);
        mainXml.tabLayout.setTabRippleColor(null);

    }

    private void init(){
        searchViewModel=new ViewModelProvider(this).get(SearchViewModel.class);
        tabLayoutAdapter=new TabLayoutAdapter(getSupportFragmentManager());
    }
}