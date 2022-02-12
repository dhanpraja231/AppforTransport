package com.example.flipr2transport.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;

import com.example.flipr2transport.LogInActivity;
import com.example.flipr2transport.R;
import com.example.flipr2transport.dealer.DealerHomePage;

import java.util.ArrayList;

public class DriverHomePage extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AppCompatButton logout;
    ArrayList<DealerModelObject> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
        logout = findViewById(R.id.driver_home_log_out_button);

        //TODO: populate datalist from bookings table
        dataList = new ArrayList<>(20); //dummy list
        mRecyclerView.setAdapter(new DriverHomePageRVAdapter(this,dataList ));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogScreen = new Intent(DriverHomePage.this, LogInActivity.class);
                startActivity(toLogScreen);
                finish();
            }
        });
    }


}