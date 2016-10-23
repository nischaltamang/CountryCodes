package com.nischaltamang.countrycodes.Data;//8/16/16, 6:25 PM

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.nischaltamang.countrycodes.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CountryList {
    private static final String TAG = "CountryList";

    private static CountryList sCountryList;

    private List<Country> mList;
    private boolean mListHasDownloaded;

    public CountryList(Context context){
        mListHasDownloaded = false;
        InputStream inputStream = context.getResources().openRawResource(R.raw.countrycode);
        new DownloadCSVTask().execute(inputStream);
    }

    public List<Country> getList(){
        return mList;
    }

    public boolean listHasDownloaded() {
        return mListHasDownloaded;
    }

    private class DownloadCSVTask extends AsyncTask<InputStream, Void, List<Country>> {
        @Override
        protected List<Country> doInBackground(InputStream... inputStreams) {
            CSVReader reader = new CSVReader(inputStreams[0]);
            return reader.read();
        }

        @Override
        protected void onPostExecute(List<Country> list) {
            mList = list;
            mListHasDownloaded = true;
            Log.i(TAG, "AsyncTask finished");
        }
    }
}
