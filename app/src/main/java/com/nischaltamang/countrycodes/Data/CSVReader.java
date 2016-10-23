package com.nischaltamang.countrycodes.Data;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static final String TAG = "CSVReader";

    private InputStream mInputStream;

    public CSVReader(InputStream inputStream){
        mInputStream = inputStream;
    }

    public List<Country> read(){
        List<Country> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                Log.i(TAG, "got country: "+ rowData[0]);

                Country country = new Country();
                if(rowData.length == 4) {
                    country.setName(rowData[0]);
                    country.setAltName(rowData[1]);
                    country.setCountryCode(rowData[2]);
                    country.setExitCode(rowData[3]);
                }
                list.add(country);
            }
        } catch (IOException ioe){
            Log.e(TAG, "Could not read in a country", ioe);
        } finally {
            try {
                mInputStream.close();
            } catch (IOException ioe){
                Log.e(TAG, "Could not close stream");
            }
        }

        Log.i(TAG, "Got a total of " + list.size() +" countries.");

        return list;

    }
}
