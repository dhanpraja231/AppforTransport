package com.example.flipr2transport;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginFragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    public LoginFragmentAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch ((position)){
            case 0:
                LogInFragment logInFragment = new LogInFragment();
                return logInFragment;
            case 1:
                SignUpDealerFragment signUpDealerFragment = new SignUpDealerFragment();
                return  signUpDealerFragment;
            case 2:
                SignUpDriverFragment signUpDriverFragment = new SignUpDriverFragment();
                return  signUpDriverFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
