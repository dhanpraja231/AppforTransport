package com.example.flipr2transport.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.Button;

import com.example.flipr2transport.LogInActivity;
import com.example.flipr2transport.LogInFragment;
import com.example.flipr2transport.R;
import com.example.flipr2transport.dealer.DealerHomePage;
import com.example.flipr2transport.dealer.DriverModelObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DriverHomePage extends AppCompatActivity {
    RecyclerView mRecyclerView;
    AppCompatButton logout,show;
    ArrayList<DealerModelObject> dataList;


    public String dealer_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home_page);
        logout = findViewById(R.id.driver_home_log_out_button);
        show = findViewById(R.id.driver_home_search_button);

        mRecyclerView = findViewById(R.id.driver_home_recycler_view);


        //TODO: populate datalist from bookings table
        dataList = new ArrayList<>(20);

        ConnectorLogin login = new ConnectorLogin();
        login.execute("");

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(dataList.toString()+"datalist");
                mRecyclerView.setAdapter(new DriverHomePageRVAdapter(DriverHomePage.this,dataList ));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(DriverHomePage.this));
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLogScreen = new Intent(DriverHomePage.this, LogInActivity.class);
                startActivity(toLogScreen);
                finish();
            }
        });
    }

    class ConnectorLogin extends AsyncTask<String,Void,String> {
        String res = "";

        public static final String url = "jdbc:mysql://192.168.0.101:3306/FLIPR"; //ip of laptop and port of xampp
        public static final String user = "hema";
        public static final String pass = "1234";


        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("running db bookings table");
                System.out.println(LogInFragment.user_name+"user");
                String result = "";

                String dealer_name_db = "",dealer_material_db="",dealer_mobile_db="",dealer_weight_db="",dealer_quantity_db="",dealer_city_db="",
                        dealer_state_db="";



                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM `BOOKING` WHERE `DRIVER_NAME` = '"+LogInFragment.user_name+"';");
                // ResultSet rs = st.executeQuery("SELECT * FROM `FOR_SALE` WHERE `EMAIL` = '"+"EMAIL"+"' ;");

               // ResultSetMetaData rsmd = rs.getMetaData();


                while (rs.next()) {                                         //-> to run with ddl
                    System.out.println("entered booking db");
                     result += rs.getString(3); // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    System.out.println("got result as:"+result);
                    Statement st2 = con.createStatement();
                    ResultSet rs2 = st2.executeQuery("SELECT * FROM `DEALER` WHERE `EMAIL` = '"+result+"';");

                        while(rs2.next())
                        {

                            dealer_name_db += rs2.getString(1);
                            System.out.println(dealer_name_db += rs2.getString(1)+"dealer info");
                            dealer_material_db += rs2.getString(5);
                            dealer_mobile_db += rs2.getString(4);
                            dealer_weight_db += rs2.getString(6);
                            dealer_quantity_db += rs2.getString(7);
                            dealer_city_db += rs2.getString(8);
                            dealer_state_db += rs2.getString(9);


                            DealerModelObject dealerModelObject = new DealerModelObject(dealer_name_db,dealer_material_db
                            ,dealer_mobile_db,dealer_weight_db,dealer_quantity_db,dealer_city_db,dealer_state_db);

                            dataList.add(dealerModelObject);


                        }

//

                    //to add to recycler view
//                    DealerModelObject dealerModelObject = new DealerModelObject(driver_name_db,driver_truck_num_db,driver_mobile_db,driver_capacity_db,driver_experience_db,driver_transporter_db,driver_age_db);
//                    dataList.add(driverModelObject);

                   // System.out.println(dataList);


                }

                dealer_name = result;


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