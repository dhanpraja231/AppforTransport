package com.example.flipr2transport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SignUpDealerFragment extends Fragment {
    public static final String url = "jdbc:mysql://192.168.0.101:3306/FLIPR"; //ip of laptop and port of xampp
    public static final String user = "hema";
    public static final String pass = "1234";
    AppCompatSpinner stateSpinner;
    AppCompatSpinner citySpinner;
    String[] stateArray;
    ArrayList<String> cityArray;
    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> cityAdapter;
    public String state,city;
    public String new_name,new_email,new_password,new_phone,new_nature,new_weight,new_quantity,new_state,new_city,new_confirmpwd;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_dealer_fragment,container,false);
        EditText name = (EditText) root.findViewById(R.id.name_sign_dealer);
        EditText email = (EditText) root.findViewById(R.id.email_sign_dealer);
        EditText password = (EditText) root.findViewById(R.id.password_sign_dealer);
        EditText confirm_pass = (EditText) root.findViewById(R.id.confirm_password_sign_dealer);
        EditText material_type = (EditText) root.findViewById(R.id.material_type_sign_dealer);

        EditText phone = (EditText) root.findViewById(R.id.mobile_sign_dealer);
        EditText material_quantity = (EditText) root.findViewById(R.id.material_quantity_sign_dealer);
        EditText material_weight = (EditText) root.findViewById(R.id.material_weight_sign_dealer);


        Button signup = (Button) root.findViewById(R.id.signup_dealer_button);

        stateSpinner = root.findViewById(R.id.dealer_sign_spinner_state);
        citySpinner  = root.findViewById(R.id.dealer_sign_spinner_city);

        stateArray = new String[]{"Andaman and Nicobar Islands","Andhra Pradesh","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Delhi",
                "Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand",
                "Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya",
                "Mizoram","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Tamil Nadu",
                "Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
        cityArray = new ArrayList<>(20);

        stateAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item,stateArray);
        cityAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_item,cityArray);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stateSpinnerValue = stateSpinner.getSelectedItem().toString();
                state = adapterView.getItemAtPosition(i).toString();
                System.out.println(state); //by default selects the first state
                //query database and add to city array
                StateCityDatabaseAccess dbAccess = StateCityDatabaseAccess.getInstance(getContext());
                dbAccess.open();
                cityArray = dbAccess.getCities(stateSpinnerValue);
              //  System.out.println(cityArray);
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

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                city = adapterView.getItemAtPosition(i).toString();
                System.out.println(city);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new_email = email.getText().toString();
               new_password = password.getText().toString();
               new_confirmpwd = confirm_pass.getText().toString();
               new_quantity = material_quantity.getText().toString();
               new_weight = material_weight.getText().toString();
               new_phone = phone.getText().toString();
               new_nature = material_type.getText().toString();
                new_state = state;
                new_city = city;
                new_name = name.getText().toString();

                if(new_confirmpwd.equals(new_password)){//mysql connector
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

                int rs = st.executeUpdate("INSERT INTO `DEALER` (`NAME`,`EMAIL`, `PASSWORD`, `PHONE`, `NATURE`, `WEIGHT`, `QUANTITY`, `STATE`, `CITY`) " +
                        "VALUES ('"+new_name+"', '"+new_email+"', '"+new_password+"', '"+new_phone+"', '"+new_nature+"', '"+new_weight+"', '"+new_quantity+"', '"+new_state+"', '"+new_city+"');");




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
