package com.example.flipr2transport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

public class LogInFragment extends Fragment {
    AppCompatSpinner stateSpinner;
    AppCompatSpinner citySpinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saverInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.log_in_fragment,container,false);






        return root;
    }


}
