package com.nischaltamang.countrycodes.UserInputs;//8/16/16, 3:24 PM

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nischaltamang.countrycodes.R;

public class PhoneNumberFragment extends Fragment {

    private EditText mPhoneEditText;
    private Callbacks mCallbacks;

    public static PhoneNumberFragment newInstance(){
        return new PhoneNumberFragment();
    }

    public interface Callbacks{
        void onPhoneEntered(String phoneNumber);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_number, container, false);

        mPhoneEditText = (EditText) view.findViewById(R.id.phone_number_search_view);
        mPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCallbacks.onPhoneEntered(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallbacks = null;
    }
}
