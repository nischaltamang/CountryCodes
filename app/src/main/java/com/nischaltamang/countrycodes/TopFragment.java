package com.nischaltamang.countrycodes;//8/16/16, 1:27 PM

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopFragment extends Fragment {

    public static final String TAG = "TopFragment";

    private TextView mFromTextView;
    private TextView mToTextView;
    private TextView mPhoneTextView;

    public static TopFragment newInstance(){
        return new TopFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top, container, false);

        mFromTextView = (TextView) v.findViewById(R.id.from_title);
        mFromTextView.setTypeface(Typeface.DEFAULT_BOLD);
        mToTextView = (TextView) v.findViewById(R.id.to_title);
        mPhoneTextView = (TextView) v.findViewById(R.id.phone_title);


        return v;
    }

    public void setTitleIndicator(int position){
        switch (position){
            case 0:
                mFromTextView.setTypeface(Typeface.DEFAULT_BOLD);

                mToTextView.setTypeface(Typeface.DEFAULT);
                mPhoneTextView.setTypeface(Typeface.DEFAULT);
                break;
            case 1:
                mToTextView.setTypeface(Typeface.DEFAULT_BOLD);

                mFromTextView.setTypeface(Typeface.DEFAULT);
                mPhoneTextView.setTypeface(Typeface.DEFAULT);
                break;
            case 2:
                mPhoneTextView.setTypeface(Typeface.DEFAULT_BOLD);

                mToTextView.setTypeface(Typeface.DEFAULT);
                mFromTextView.setTypeface(Typeface.DEFAULT);
                break;
            default:
                Log.e(TAG, "Invalid position");
                break;
        }
    }
}
