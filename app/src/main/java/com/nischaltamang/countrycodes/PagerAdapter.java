package com.nischaltamang.countrycodes;//8/16/16, 2:30 PM

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nischaltamang.countrycodes.UserInputs.FromFragment;
import com.nischaltamang.countrycodes.UserInputs.PhoneNumberFragment;
import com.nischaltamang.countrycodes.UserInputs.ToFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = FromFragment.newInstance();
                break;
            case 1:
                fragment = ToFragment.newInstance();
                break;
            case 2:
                fragment = PhoneNumberFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        String title = "";

        switch (position){
            case 0:
                title = "From";
                break;
            case 1:
                title = "To";
                break;
            case 2:
                title = "Phone Number";
                break;
        }

        return title;
    }
}
