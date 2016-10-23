package com.nischaltamang.countrycodes.UserInputs;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.nischaltamang.countrycodes.Data.CSVReader;
import com.nischaltamang.countrycodes.Data.Country;
import com.nischaltamang.countrycodes.Data.CountryList;
import com.nischaltamang.countrycodes.R;
import com.nischaltamang.countrycodes.Data.Suggestions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FromFragment extends Fragment {
    private static final String TAG = "FromFragment";

//    private List<Country> mCountryList;
    private List<Country> mSuggestionList;
    private FloatingSearchView mFromSearchView;
    private CountryList mCountryList;

    private Callbacks mCallbacks;

    public static FromFragment newInstance(){
        return new FromFragment();
    }

    public interface Callbacks{
        void onFromCountrySelected(Country country);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCountryList = new CountryList(getActivity());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_from, container, false);

        mSuggestionList = new ArrayList<>();

        mFromSearchView = (FloatingSearchView) v.findViewById(R.id.from_search_view);
        mFromSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

                Suggestions.findSuggestions(getActivity(), mCountryList.getList(), newQuery, 5, new Suggestions.OnFindSuggestionsListener() {
                    @Override
                    public void onResults(List<Country> results) {
                        mSuggestionList = results;
                        mFromSearchView.swapSuggestions(results);
                    }
                });
            }
        });

        mFromSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Country selectedCountry = (Country) searchSuggestion;
                mCallbacks.onFromCountrySelected(selectedCountry);
            }

            @Override
            public void onSearchAction(String currentQuery) {
                if(mSuggestionList != null && !mSuggestionList.isEmpty()) {
                    mFromSearchView.clearSearchFocus();
                    Country firstCountry = mSuggestionList.get(mSuggestionList.size()-1);
                    mFromSearchView.setSearchText(firstCountry.getName());
                    mCallbacks.onFromCountrySelected(firstCountry);
                }
            }
        });

        return v;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallbacks = null;
    }
}
