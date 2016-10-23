package com.nischaltamang.countrycodes;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nischaltamang.countrycodes.Data.Country;
import com.nischaltamang.countrycodes.UserInputs.FromFragment;
import com.nischaltamang.countrycodes.UserInputs.PhoneNumberFragment;
import com.nischaltamang.countrycodes.UserInputs.ToFragment;

public class MainActivity extends AppCompatActivity implements FromFragment.Callbacks, ToFragment.Callbacks, PhoneNumberFragment.Callbacks {

    private ViewPager mViewPager;
    private Fragment mHeaderFragment;
    private Fragment mResultsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mHeaderFragment = fragmentManager.findFragmentById(R.id.top_container);
        mResultsFragment = fragmentManager.findFragmentById(R.id.bottom_container);
        if(mHeaderFragment == null && mResultsFragment == null){
            mHeaderFragment = TopFragment.newInstance();
            mResultsFragment = ResultsFragment.newInstance();

            fragmentManager.beginTransaction().add(R.id.top_container, mHeaderFragment).commit();
            fragmentManager.beginTransaction().add(R.id.bottom_container, mResultsFragment).commit();
        }

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TopFragment topFragment = (TopFragment) mHeaderFragment;
                topFragment.setTitleIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onFromCountrySelected(Country country) {
        ResultsFragment resultsFragment = (ResultsFragment) mResultsFragment;
        resultsFragment.setExitCodeText(country.getExitCode());
    }

    @Override
    public void onToCountrySelected(Country country) {
        ResultsFragment resultsFragment = (ResultsFragment) mResultsFragment;
        resultsFragment.setCountryCodeText(country.getCountryCode());
    }


    @Override
    public void onPhoneEntered(String phoneNumber) {
        ResultsFragment resultsFragment = (ResultsFragment) mResultsFragment;
        resultsFragment.setPhoneText(phoneNumber);
    }
}
