package com.example.flipr2transport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SignUpDriverFragment extends Fragment {
    public static final String url = "jdbc:mysql://192.168.0.101:3306/FLIPR"; //ip of laptop and port of xampp
    public static final String user = "hema";
    public static final String pass = "1234";
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

    public String new_transporter,new_name,new_email,new_phone,new_age,new_trucknum,new_capacity,new_exp,new_password,new_confirmpwd;

    public String from_state1,from_city1,to_state1,to_city1;
    public String from_state2,from_city2,to_state2,to_city2;
    public String from_state3,from_city3,to_state3,to_city3;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_driver_fragment,container,false);

            EditText email = (EditText) root.findViewById(R.id.email_sign_driver);
        EditText mobile = (EditText) root.findViewById(R.id.mobile_sign_driver);
        EditText name = (EditText) root.findViewById(R.id.name_sign_driver);
        EditText age =(EditText) root.findViewById(R.id.age_sign_driver);
        EditText truck_capacity =(EditText) root.findViewById(R.id.truck_capacity_sign_driver);
        EditText truck_num =(EditText) root.findViewById(R.id.truck_no_sign_driver);
        EditText transporter =(EditText) root.findViewById(R.id.transporter_sign_driver);
        EditText experience =(EditText) root.findViewById(R.id.experience_sign_driver);
        EditText password =(EditText) root.findViewById(R.id.password_sign_driver);
        EditText confirm_pass =(EditText) root.findViewById(R.id.confirm_password_sign_driver);
        Button signup = (Button) root.findViewById(R.id.signup_driver_button) ;







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

        cityAdapterp1 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item);
        cityAdapterp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterp2 = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item);
        cityAdapterp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterp3 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item);
        cityAdapterp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterd1 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item);
        cityAdapterd1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterd2 = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item);
        cityAdapterd2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityAdapterd3 = new ArrayAdapter<String>(this.getContext(),R.layout.spinner_item);
        cityAdapterd3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stateAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item,stateArray);
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
                from_state1 = stateSpinnerValuesp1;
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
        citySpinnercp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO
                from_city1  = adapterView.getItemAtPosition(i).toString();

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
                from_state2 = stateSpinnerValuesp2;
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
        citySpinnercp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from_city2  = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        stateSpinnersp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesp3 = stateSpinnersp3.getSelectedItem().toString();
                from_state3 = stateSpinnerValuesp3;
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
        citySpinnercp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from_city3  = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersd1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValue = stateSpinnersd1.getSelectedItem().toString();
                to_state1 = stateSpinnerValue;
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
        citySpinnercd1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to_city1  = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersd2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesd2 = stateSpinnersd2.getSelectedItem().toString();
                to_state2 = stateSpinnerValuesd2;
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
        citySpinnercd2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to_city2  = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateSpinnersd3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValuesd3 = stateSpinnersd3.getSelectedItem().toString();
                to_state3 = stateSpinnerValuesd3;
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
        citySpinnercd3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to_city3 = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new_age = age.getText().toString();
                new_capacity = truck_capacity.getText().toString();
                new_phone = mobile.getText().toString();
                new_exp = experience.getText().toString();
                new_email = email.getText().toString();
                new_name = name.getText().toString();
                new_transporter = transporter.getText().toString();
                new_trucknum = truck_num.getText().toString();
                new_password = password.getText().toString();
                new_confirmpwd = confirm_pass.getText().toString();
                new_transporter = transporter.getText().toString();
                if(new_password.equals(new_confirmpwd)){
                    ConnectMySQL login = new ConnectMySQL();
                    login.execute("");
                }else{
                    Toast.makeText(getActivity(),"kindly confirm password again",Toast.LENGTH_SHORT).show();
                }


            }
        });







        return root;
    }

    private class ConnectMySQL extends AsyncTask<String,Void,String> {
        String res = "";

        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");


                String result = "Database Connection Successful\n";
                Statement st = con.createStatement();

                int rs = st.executeUpdate("INSERT INTO `DRIVER` (`NAME`, `AGE`, `EMAIL`, `PHONE`, `TRUCK_NUM`, `CAPACITY`, `EXPERIENCE`, `FROM_STATE_1`, `FROM_CITY_1`, `TO_STATE_1`, `TO_CITY_1`, `FROM_STATE_2`, `FROM_CITY_2`, `TO_STATE_2`, `TO_CITY_2`, `FROM_STATE_3`, `FROM_CITY_3`,`TO_STATE_3`,`TO_CITY_3`, `PASSWORD`, `TRANSPORTER`) " +
                        "VALUES ('"+new_name+"', '"+new_age+"', '"+new_email+"', '"+new_phone+"', '"+new_trucknum+"', '"+new_capacity+"', '"+new_exp+"', '"+from_state1+"', '"+from_city1+"', '"+to_state1+"', '"+to_city1+"', '"+from_state2+"', '"+from_city2+"', '"+to_state2+"', '"+to_city2+"', '"+from_state3+"', '"+from_city3+"', '"+to_state3+"', '"+to_city3+"', '"+new_password+"', '"+new_transporter+"');");




                res = result;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getActivity(), "successfully signed up", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getActivity(), Homepage.class);  //from and to ----------------------------!!!!!!!!
//            startActivity(intent);  //to open login page
        }
    }















}
