package com.example.flipr2transport;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class LogInFragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioButton;


    public String email_entered,password_entered;
    public static final String url = "jdbc:mysql://192.168.0.101:3306/FLIPR"; //ip of laptop and port of xampp
    public static final String user = "hema";
    public static final String pass = "1234";
    Random random = new Random();
    // Generates random integers 0 to 49
    public int int_otp_gen = random.nextInt(10000);
    public String otp_gen = String.valueOf(int_otp_gen);


    public String otp_entered;

    public String occupation;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.log_in_fragment,container,false);

        // Bind the components to their respective objects
        // by assigning their IDs

        radioGroup = (RadioGroup)root.findViewById(R.id.radio_group_log_in_as);

        // Uncheck or reset the radio buttons initially
        radioGroup.clearCheck();



        EditText email = (EditText) root.findViewById(R.id.email_login);
        EditText otp = (EditText) root.findViewById(R.id.otp_login);
        EditText password = (EditText) root.findViewById(R.id.password_login);

        Button sendotp = (Button) root.findViewById(R.id.send_otp);
        Button login = (Button) root.findViewById(R.id.login_button);
        Button login_otp = (Button) root.findViewById(R.id.login_otp_button);

        login_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (otp_entered.equals(otp_gen)){
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        radioButton = (RadioButton) root.findViewById(radioId);

                        occupation = radioButton.getText().toString();
                        String dealer = "DEALER";
                        String driver = "DRIVER";
                        if (occupation.equals(driver)) {


                            //for driver

                        } else if (occupation.equals(dealer)) {
                            System.out.println("in dealer");
                            ConnectorLogindealer login = new ConnectorLogindealer();
                            login.execute("");
                            //for dealer
                        }

                    }
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) root.findViewById(radioId);

                    occupation = radioButton.getText().toString();

                    System.out.println("radio" + occupation);

                    email_entered = email.getText().toString();
                    password_entered = password.getText().toString();
                    String dealer = "DEALER";
                    String driver = "DRIVER";
                    if (occupation.equals(driver)) {
                        //for driver
                        ConnectorLogin2 login2 = new ConnectorLogin2();
                        login2.execute("");
                    } else if (occupation.equals(dealer)) {
                        System.out.println("in dealer");
                        ConnectorLogin login = new ConnectorLogin();
                        login.execute("");
                        //for dealer
                    }


//
//
//                ConnectorLogin login = new ConnectorLogin();
//                login.execute("");

            }
        });

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String dealer ="DEALER";
                String driver = "DRIVER";

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) root.findViewById(radioId);

                occupation = radioButton.getText().toString();
                if(occupation.equals(driver)){
                    //for driver
//                   ConnectorLogin2 login2 = new ConnectorLogin2();
//                    login2.execute("");
                }
                else if (occupation.equals(dealer)){
                    System.out.println("in dealer");
                    ConnectorLoginotpdealer login = new ConnectorLoginotpdealer();
                    login.execute("");
                    //for dealer to check if email exists and log them in
                }

                otp_entered= otp.getText().toString();



            }
        });
        return root;
    }



    private class ConnectorLogin extends AsyncTask<String,Void,String> {
        String res = "";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                String output = "";
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM `DEALER` WHERE `EMAIL` = '"+email_entered+"' AND `PASSWORD` = '"+password_entered+"';");



                ResultSetMetaData rsmd = rs.getMetaData();
              //  user user1 = new user();
                while (rs.next()) {                                         //-> to run with ddl
                    output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                 //   user1.name = rs.getString(1).toString();
                 //   user1.email = rs.getString(2).toString();
                 //   user1.phone = rs.getString(4);

                }

                System.out.println("user"+output);

              //  new_username = user1.getName();
              //  new_email = user1.getEmail();
             //   new_phone = user1.getPhone();
                res = output;



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(res+"res");
            return res;

        }


        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);


            if (s==null){
                Toast.makeText(getActivity(),"INVALID DETAILS QQ", Toast.LENGTH_SHORT).show();
            }
            else if (s!=null) {

                Toast.makeText(getActivity(), "WELCOME BACK "+s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Homepage.class);  //from and to ----------------------------!!!!!!!!
                startActivity(intent);  //to open login page

            }
        }
    }


    private class ConnectorLogin2 extends AsyncTask<String,Void,String> {
        String res = "";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                String output = null;
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM `DRIVER` WHERE `EMAIL` = '"+email_entered+"' AND `PASSWORD` = '"+password_entered+"';");



                ResultSetMetaData rsmd = rs.getMetaData();
                //  user user1 = new user();
                while (rs.next()) {                                         //-> to run with ddl
                    output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    //   user1.name = rs.getString(1).toString();
                    //   user1.email = rs.getString(2).toString();
                    //   user1.phone = rs.getString(4);

                }

                System.out.println("user"+output);

                //  new_username = user1.getName();
                //  new_email = user1.getEmail();
                //   new_phone = user1.getPhone();
                res = output;



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(res+"res");
            return res;

        }


        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);


            if (s!=null){
                Toast.makeText(getActivity(), "WELCOME BACK "+s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Homepage.class);  //from and to ----------------------------!!!!!!!!
                startActivity(intent);  //to open login page

            }
            else  {
                Toast.makeText(getActivity(),"INVALID DETAILS QQ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class ConnectorLogindealer extends AsyncTask<String,Void,String> {
        String res = "";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                String output = "";
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM `DEALER` WHERE `EMAIL` = '"+email_entered+"';");



                ResultSetMetaData rsmd = rs.getMetaData();
                //  user user1 = new user();
                while (rs.next()) {                                         //-> to run with ddl
                    output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    //   user1.name = rs.getString(1).toString();
                    //   user1.email = rs.getString(2).toString();
                    //   user1.phone = rs.getString(4);

                }

                System.out.println("user"+output);

                //  new_username = user1.getName();
                //  new_email = user1.getEmail();
                //   new_phone = user1.getPhone();
                res = output;



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(res+"res");
            return res;

        }


        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);


            if (s==null){
                Toast.makeText(getActivity(),"INVALID DETAILS QQ", Toast.LENGTH_SHORT).show();
            }
            else if (s!=null) {

                Toast.makeText(getActivity(), "WELCOME BACK "+s, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Homepage.class);  //from and to ----------------------------!!!!!!!!
                startActivity(intent);  //to open login page

            }
        }
    }








    private class ConnectorLoginotpdealer extends AsyncTask<String,Void,String> {
        String res = "";
        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                System.out.println("Databaseection success");

                String result = "Database Connection Successful\n";
                String output = "";
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM `DEALER` WHERE `EMAIL` = '"+email_entered+"';");



                ResultSetMetaData rsmd = rs.getMetaData();
                //  user user1 = new user();
                while (rs.next()) {                                         //-> to run with ddl
                    output += rs.getString(1).toString() + "\n"; // TO DETERMINE WHICH COLUMN INFO WE ARE GETTING!
                    //   user1.name = rs.getString(1).toString();
                    //   user1.email = rs.getString(2).toString();
                    //   user1.phone = rs.getString(4);

                }

                System.out.println("user"+output);

                //  new_username = user1.getName();
                //  new_email = user1.getEmail();
                //   new_phone = user1.getPhone();
                res = output;



            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(res+"res");
            return res;

        }


        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);


            if (s!=null){

                SendMail sm = new SendMail(getActivity(),email_entered,"OTP FOR LOGIN","YOUR OTP IS"+otp_gen);
                sm.execute();
               Toast.makeText(getActivity(), "OTP has been sent", Toast.LENGTH_SHORT).show();

//                Toast.makeText(getActivity(), "WELCOME BACK "+s, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), Homepage.class);  //from and to ----------------------------!!!!!!!!
//                startActivity(intent);  //to open login page

            }
            else  {
                Toast.makeText(getActivity(),"INVALID DETAILS ", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
