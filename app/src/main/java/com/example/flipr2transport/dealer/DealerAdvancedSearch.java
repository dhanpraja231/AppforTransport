package com.example.flipr2transport.dealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.flipr2transport.R;
import com.example.flipr2transport.StateCityDatabaseAccess;

import java.util.ArrayList;

public class DealerAdvancedSearch extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AppCompatButton mQueryDatabaseButton;
    AppCompatSpinner spSpinner;
    AppCompatSpinner cpSpinner;
    AppCompatSpinner sdSpinner;
    AppCompatSpinner cdSpinner;
    String[] stateArray;
    ArrayList<String> cpArray;
    ArrayList<String> cdArray;
    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> cpAdapter;
    ArrayAdapter<String> cdAdapter;
    ArrayList<DriverModelObject> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_advanced_search);
        mRecyclerView = findViewById(R.id.dealer_advanced_recyclerview);
        mQueryDatabaseButton = findViewById(R.id.confirm_advanced_search);
        spSpinner = findViewById(R.id.dealer_advanced_sp);
        cpSpinner  = findViewById(R.id.dealer_advanced_cp);
        sdSpinner = findViewById(R.id.dealer_advanced_sd);
        cdSpinner  = findViewById(R.id.dealer_advanced_cd);

        stateArray = new String[]{"Andaman and Nicobar Islands","Andhra Pradesh","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Delhi",
                "Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand",
                "Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya",
                "Mizoram","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Tamil Nadu",
                "Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};

        stateAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,stateArray);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(stateAdapter);
        sdSpinner.setAdapter(stateAdapter);

        cpAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cdAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValue = spSpinner.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getBaseContext());
                dbAccess.open();
                cpArray = dbAccess.getCities(stateSpinnerValue);
                System.out.println(cpArray);
                cpAdapter.clear();
                cpAdapter.addAll(cpArray);
                //set adapter to spinner
                cpSpinner.setAdapter(cpAdapter);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValue = sdSpinner.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getBaseContext());
                dbAccess.open();
                cdArray = dbAccess.getCities(stateSpinnerValue);
                System.out.println(cdArray);
                cdAdapter.clear();
                cdAdapter.addAll(cdArray);
                //set adapter to spinner
                cdSpinner.setAdapter(cdAdapter);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        mQueryDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:query database by getting state and city from spinner selected text
                dataList = new ArrayList<>(20); //dummy list
          //      dataList.addAll()

                mRecyclerView.setAdapter(new DealerHomePageRVAdapter(DealerAdvancedSearch.this,dataList ));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(DealerAdvancedSearch.this));
            }
        });
    }
}