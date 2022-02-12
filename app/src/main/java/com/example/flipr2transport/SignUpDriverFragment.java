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

public class SignUpDriverFragment extends Fragment {
    AppCompatSpinner stateSpinnersp1;
    AppCompatSpinner citySpinnercp1;
    AppCompatSpinner stateSpinnersp2;
    AppCompatSpinner citySpinnercp2;
    AppCompatSpinner stateSpinnersp3;
    AppCompatSpinner citySpinnercp3;


    AppCompatSpinner stateSpinnersd1;
    AppCompatSpinner citySpinnercd1;
    AppCompatSpinner stateSpinnersd2;
    AppCompatSpinner citySpinnercd2;
    AppCompatSpinner stateSpinnersd3;
    AppCompatSpinner citySpinnercd3;

    String[] stateArray;
    ArrayList<String> cityArraycp1;
    ArrayList<String> cityArraycp2;
    ArrayList<String> cityArraycp3;
    ArrayList<String> cityArraycd1;
    ArrayList<String> cityArraycd2;
    ArrayList<String> cityArraycd3;

    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> cityAdapterp1;
    ArrayAdapter<String> cityAdapterp2;
    ArrayAdapter<String> cityAdapterp3;
    ArrayAdapter<String> cityAdapterd1;
    ArrayAdapter<String> cityAdapterd2;
    ArrayAdapter<String> cityAdapterd3;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_driver_fragment,container,false);

        stateSpinnersp1 = root.findViewById(R.id.driver_sign_spinner_sp1);
        citySpinnercp1  = root.findViewById(R.id.driver_sign_spinner_cp1);
        stateSpinnersp2 = root.findViewById(R.id.driver_sign_spinner_sp2);
        citySpinnercp2  = root.findViewById(R.id.driver_sign_spinner_cp2);
        stateSpinnersp3 = root.findViewById(R.id.driver_sign_spinner_sp3);
        citySpinnercp3  = root.findViewById(R.id.driver_sign_spinner_cp3);
        stateSpinnersd1 = root.findViewById(R.id.driver_sign_spinner_sd1);
        citySpinnercd1  = root.findViewById(R.id.driver_sign_spinner_cd1);
        stateSpinnersd2 = root.findViewById(R.id.driver_sign_spinner_sd2);
        citySpinnercd2  = root.findViewById(R.id.driver_sign_spinner_cd2);
        stateSpinnersd3 = root.findViewById(R.id.driver_sign_spinner_sd3);
        citySpinnercd3  = root.findViewById(R.id.driver_sign_spinner_cd3);

        stateArray = new String[]{"Andaman and Nicobar Islands","Andhra Pradesh","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Delhi",
                "Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand",
                "Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya",
                "Mizoram","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Tamil Nadu",
                "Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
        cityArraycp1 = new ArrayList<>(20);
        cityArraycp2 = new ArrayList<>(20);
        cityArraycp3 = new ArrayList<>(20);
        cityArraycd1 = new ArrayList<>(20);
        cityArraycd2 = new ArrayList<>(20);
        cityArraycd3 = new ArrayList<>(20);

        cityAdapterp1 = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cityAdapterp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterp2 = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cityAdapterp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterp3 = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cityAdapterp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterd1 = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cityAdapterd1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterd2 = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cityAdapterd2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterd3 = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        cityAdapterd3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateAdapter = new ArrayAdapter<String>(this.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,stateArray);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinnersp1.setAdapter(stateAdapter);
        stateSpinnersp2.setAdapter(stateAdapter);
        stateSpinnersp3.setAdapter(stateAdapter);
        stateSpinnersd1.setAdapter(stateAdapter);
        stateSpinnersd2.setAdapter(stateAdapter);
        stateSpinnersd3.setAdapter(stateAdapter);

        stateSpinnersp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesp1 = stateSpinnersp1.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArraycp1 = dbAccess.getCities(stateSpinnerValuesp1);
                System.out.println(cityArraycp1);
                cityAdapterp1.clear();
                cityAdapterp1.addAll(cityArraycp1);
                //set adapter to spinner
                citySpinnercp1.setAdapter(cityAdapterp1);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesp2 = stateSpinnersp2.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArraycp2 = dbAccess.getCities(stateSpinnerValuesp2);
                System.out.println(cityArraycp2);
                cityAdapterp2.clear();
                cityAdapterp2.addAll(cityArraycp2);
                //set adapter to spinner
                citySpinnercp2.setAdapter(cityAdapterp2);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesp3 = stateSpinnersp3.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArraycp3 = dbAccess.getCities(stateSpinnerValuesp3);
                System.out.println(cityArraycp3);
                cityAdapterp3.clear();
                cityAdapterp3.addAll(cityArraycp3);
                //set adapter to spinner
                citySpinnercp3.setAdapter(cityAdapterp3);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersd1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValue = stateSpinnersd1.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArraycd1 = dbAccess.getCities(stateSpinnerValue);
                System.out.println(cityArraycd1);
                cityAdapterd1.clear();
                cityAdapterd1.addAll(cityArraycd1);
                //set adapter to spinner
                citySpinnercd1.setAdapter(cityAdapterd1);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersd2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesd2 = stateSpinnersd2.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArraycd2 = dbAccess.getCities(stateSpinnerValuesd2);
                System.out.println(cityArraycd2);
                cityAdapterd2.clear();
                cityAdapterd2.addAll(cityArraycd2);
                //set adapter to spinner
                citySpinnercd2.setAdapter(cityAdapterd2);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersd3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesd3 = stateSpinnersd3.getSelectedItem().toString();
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArraycd3 = dbAccess.getCities(stateSpinnerValuesd3);
                System.out.println(cityArraycd3);
                cityAdapterd3.clear();
                cityAdapterd3.addAll(cityArraycd3);
                //set adapter to spinner
                citySpinnercd3.setAdapter(cityAdapterd3);
                dbAccess.close();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
    }
}
