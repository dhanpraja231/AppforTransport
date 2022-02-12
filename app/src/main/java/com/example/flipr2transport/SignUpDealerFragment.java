package com.example.flipr2transport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SignUpDealerFragment extends Fragment {

    AppCompatSpinner stateSpinner;
    AppCompatSpinner citySpinner;
    String[] stateArray;
    ArrayList<String> cityArray;
    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> cityAdapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_dealer_fragment,container,false);

        stateSpinner = root.findViewById(R.id.dealer_sign_spinner_state);
        citySpinner  = root.findViewById(R.id.dealer_sign_spinner_city);

        stateArray = new String[]{"Andaman and Nicobar Islands","Andhra Pradesh","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Delhi",
                "Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand",
                "Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya",
                "Mizoram","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Tamil Nadu",
                "Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
        cityArray = new ArrayList<>(20);

        stateAdapter = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,stateArray);
        cityAdapter = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cityArray);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValue = stateSpinner.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArray = dbAccess.getCities(stateSpinnerValue);
                System.out.println(cityArray);
                cityAdapter.clear();
                cityAdapter.addAll(cityArray);
                //set adapter to spinner
                citySpinner.setAdapter(cityAdapter);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return root;
    }
}
