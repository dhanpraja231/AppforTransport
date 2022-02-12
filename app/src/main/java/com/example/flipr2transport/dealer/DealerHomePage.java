package com.example.flipr2transport.dealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.flipr2transport.LogInActivity;
import com.example.flipr2transport.R;

import java.util.ArrayList;

public class DealerHomePage extends AppCompatActivity {
    AppCompatButton advancedSearch;
    AppCompatButton logOut;
    RecyclerView recommendedDriversRecyclerView;
    ArrayList<DriverModelObject> dataList;
    RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_home_page);
        recommendedDriversRecyclerView = findViewById(R.id.dealer_home_recyclerview);
        advancedSearch = findViewById(R.id.dealer_home_search_button);
        logOut = findViewById(R.id.dealer_home_log_out);

        //TODO: populate datalist by querying database
        dataList = new ArrayList<>(20); //dummy list

        mAdapter = new DealerHomePageRVAdapter(this,dataList);
        recommendedDriversRecyclerView.setAdapter(mAdapter);
        recommendedDriversRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        advancedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAdvancedSearch = new Intent(DealerHomePage.this, DealerAdvancedSearch.class);
                startActivity(toAdvancedSearch);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogScreen = new Intent(DealerHomePage.this, LogInActivity.class);
                startActivity(toLogScreen);
                finish();
            }
        });

    }
}