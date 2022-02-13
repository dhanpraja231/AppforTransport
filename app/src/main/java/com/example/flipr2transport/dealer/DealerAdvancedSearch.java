package com.example.flipr2transport.dealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.flipr2transport.LogInFragment;
import com.example.flipr2transport.R;
import com.example.flipr2transport.StateCityDatabaseAccess;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DealerAdvancedSearch extends AppCompatActivity {
    RecyclerView mRecyclerView;
    FloatingActionButton mQueryDatabaseButton;
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
  //  RecyclerView.Adapter mAdapter;
    public String state_selected_from,city_selected_from,state_selected_to,city_selected_to;

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

        dataList = new ArrayList<>(20);

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
                state_selected_from = adapterView.getItemAtPosition(i).toString();
                System.out.println("state selected"+state_selected_from);
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

        cpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    city_selected_from = adapterView.getItemAtPosition(i).toString();
                System.out.println("city selected"+city_selected_from);
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
                state_selected_to = adapterView.getItemAtPosition(i).toString();
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

        cdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    city_selected_to = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        mQueryDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:query database by getting state and city from spinner selected text
                ConnectorLogin login = new ConnectorLogin();
                login.execute("");


                mRecyclerView.setAdapter(new DealerHomePageRVAdapter(DealerAdvancedSearch.this,dataList ));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(DealerAdvancedSearch.this));
            }
        });
    }


    class ConnectorLogin extends AsyncTask<String,Void,String> {
        String res = "";
        // public static String n1,n2,n3,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20;
        // public static String n4,n5,n6;
        public static final String url = "jdbc:mysql://192.168.0.101:3306/FLIPR"; //ip of laptop and port of xampp
        public static final String user = "hema";
        public static final String pass = "1234";


        public String image;
        public ArrayList<String> resultList = new ArrayList<String>();
        public  ArrayList<String> resultList4 = new ArrayList<String>();
        public  ArrayList<String> resultList5 = new ArrayList<String>();
        public  ArrayList<String> resultList2 = new ArrayList<String>();
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("buy success");

                String result = "Database Connection Successful\n";


                String driver_name_db = "";
                String driver_truck_num_db = "";
                String driver_mobile_db = "";
                String driver_capacity_db = "";
                String driver_experience_db = "";
                String driver_transporter_db= "";
                String driver_age_db = "";
                String driver_email_db = "";

                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT `NAME`,`TRUCK_NUM`,`PHONE`,`CAPACITY`,`EXPERIENCE`,`TRANSPORTER`,`AGE`,`EMAIL` FROM `DRIVER` WHERE (`FROM_STATE_1` = '"+ state_selected_from +"' AND `FROM_CITY_1` = '"+city_selected_from+"' AND `TO_STATE_1` = '"+state_selected_to+"' AND `TO_CITY_1` = '"+city_selected_to+"') OR (`FROM_STATE_2` = '"+state_selected_from+"' AND `FROM_CITY_2` = '"+city_selected_from+"' AND `TO_STATE_2` = '"+state_selected_to+"' AND `TO_CITY_2` = '"+city_selected_to+"') OR (`FROM_STATE_3` = '"+state_selected_from+"' AND `FROM_CITY_3` = '"+city_selected_from+"' AND `TO_STATE_3` = '"+state_selected_to+"' AND `TO_CITY_3` = '"+city_selected_to+"');");
                // ResultSet rs = st.executeQuery("SELECT * FROM `FOR_SALE` WHERE `EMAIL` = '"+"EMAIL"+"' ;");

                ResultSetMetaData rsmd = rs.getMetaData();


                while (rs.next()) {                                         //-> to run with ddl
                    // result += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!

                    driver_name_db += rs.getString(1);
                    driver_truck_num_db += rs.getString(2);
                    driver_mobile_db += rs.getString(3);
                    driver_capacity_db += rs.getString(4);
                    driver_experience_db += rs.getString(5);
                    driver_transporter_db += rs.getString(6);
                    driver_age_db += rs.getString(7);
                    driver_email_db += rs.getString(8);

                    //to add to recycler view
                    DriverModelObject driverModelObject = new DriverModelObject(driver_name_db,driver_truck_num_db,driver_mobile_db,driver_capacity_db,driver_experience_db,driver_transporter_db,driver_age_db);
                    dataList.add(driverModelObject);

                    System.out.println(dataList);


                }


                res = result;



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return res;
        }


        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);


        }

    }










}