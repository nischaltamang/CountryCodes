package com.nischaltamang.countrycodes;//8/16/16, 1:27 PM

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultsFragment extends Fragment {

    private EditText mExitCodeEditText;
    private EditText mCountryCodeEditText;
    private EditText mPhoneEditText;
    private Button mDialButton;
    private Button mCopyButton;

    public static ResultsFragment newInstance(){
        return new ResultsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_results, container, false);

        mExitCodeEditText = (EditText) v.findViewById(R.id.exit_code_edit_text);
        mCountryCodeEditText = (EditText) v.findViewById(R.id.country_code_edit_text);
        mPhoneEditText = (EditText) v.findViewById(R.id.phone_number_edit_text);

        mDialButton = (Button) v.findViewById(R.id.dial_button);
        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dial the phone number
                String exitCode = mExitCodeEditText.getText().toString();
                String countryCode = mCountryCodeEditText.getText().toString();
                String phoneNumber = mPhoneEditText.getText().toString();

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + exitCode + countryCode + phoneNumber));
                startActivity(intent);
            }
        });
        mCopyButton = (Button) v.findViewById(R.id.copy_button);
        mCopyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Copy the number to clipboard
            }
        });
        return v;
    }

    public void setExitCodeText(String exitCode){
        mExitCodeEditText.setText(exitCode);
    }

    public void setCountryCodeText(String countryCode){
        mCountryCodeEditText.setText(countryCode);
    }

    public void setPhoneText(String phoneNumber){
        mPhoneEditText.setText(phoneNumber);
    }
}
