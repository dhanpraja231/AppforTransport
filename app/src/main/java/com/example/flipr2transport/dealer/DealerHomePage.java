package com.example.flipr2transport.dealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.flipr2transport.LogInActivity;
import com.example.flipr2transport.LogInFragment;
import com.example.flipr2transport.R;
import com.example.flipr2transport.driver.DealerModelObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DealerHomePage extends AppCompatActivity {
    AppCompatButton advancedSearch;
    AppCompatButton logOut,show;
    RecyclerView recommendedDriversRecyclerView;
    ArrayList<DriverModelObject> dataList;
    RecyclerView.Adapter mAdapter;

    public static String driver_name_db, driver_email_db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList = new ArrayList<>(20);
        setContentView(R.layout.activity_dealer_home_page);
        recommendedDriversRecyclerView = findViewById(R.id.dealer_home_recyclerview);
        recommendedDriversRecyclerView.setHasFixedSize(true);
        advancedSearch = findViewById(R.id.dealer_home_search_button);
        logOut = findViewById(R.id.dealer_home_log_out);
        show = findViewById(R.id.dealer_home_show_recycler_button);

        ConnectorLogin login = new ConnectorLogin();
        login.execute("");



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mAdapter = new DealerHomePageRVAdapter(DealerHomePage.this,dataList);
                recommendedDriversRecyclerView.setLayoutManager(new LinearLayoutManager(DealerHomePage.this));
                recommendedDriversRecyclerView.setAdapter(mAdapter);


            }
        });
        //TODO: populate datalist by querying database


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

                ResultSet rs = st.executeQuery("SELECT `NAME`,`TRUCK_NUM`,`PHONE`,`CAPACITY`,`EXPERIENCE`,`TRANSPORTER`,`AGE`,`EMAIL` FROM `DRIVER` WHERE (`FROM_STATE_1` = '"+ LogInFragment.user_state +"' AND `FROM_CITY_1` = '"+LogInFragment.user_city+"') OR (`FROM_STATE_2` = '"+LogInFragment.user_state+"' AND `FROM_CITY_2` = '"+LogInFragment.user_city+"') OR (`FROM_STATE_3` = '"+LogInFragment.user_state+"' AND `FROM_CITY_3` = '"+LogInFragment.user_city+"');");
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