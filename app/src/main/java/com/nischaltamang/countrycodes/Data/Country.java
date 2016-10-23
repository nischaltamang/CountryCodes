package com.nischaltamang.countrycodes.Data;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class Country implements SearchSuggestion {

    private String mName;
    private String mAltName;
    private String mCountryCode;
    private String mExitCode;

    public Country(){
    }

    public Country(Parcel source){
        mName = source.readString();
    }

    public String getAltName() {
        return mAltName;
    }

    public void setAltName(String altName) {
        mAltName = altName;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getExitCode() {
        return mExitCode;
    }

    public void setExitCode(String exitCode) {
        mExitCode = exitCode;
    }

    public String getBody(){
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
