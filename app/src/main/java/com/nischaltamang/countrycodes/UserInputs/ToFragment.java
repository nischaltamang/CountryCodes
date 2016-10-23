package com.nischaltamang.countrycodes.UserInputs;//8/16/16, 3:22 PM

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.nischaltamang.countrycodes.Data.Country;
import com.nischaltamang.countrycodes.Data.CountryList;
import com.nischaltamang.countrycodes.Data.Suggestions;
import com.nischaltamang.countrycodes.R;

import java.util.ArrayList;
import java.util.List;

public class ToFragment extends Fragment {
    private static final String TAG = "ToFragment";

    private CountryList mCountryList;
    private List<Country> mSuggestionList;
    private FloatingSearchView mToSearchView;

    private Callbacks mCallbacks;

    public static ToFragment newInstance(){
        return new ToFragment();
    }

    public interface Callbacks{
        void onToCountrySelected(Country country);
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
        View v = inflater.inflate(R.layout.fragment_to, container, false);
        mSuggestionList = new ArrayList<>();

        mToSearchView = (FloatingSearchView) v.findViewById(R.id.to_search_view);
        mToSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

                Suggestions.findSuggestions(getActivity(), mCountryList.getList(), newQuery, 5, new Suggestions.OnFindSuggestionsListener() {
                    @Override
                    public void onResults(List<Country> results) {
                        mSuggestionList = results;
                        mToSearchView.swapSuggestions(results);
                    }
                });
            }
        });

        mToSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Country selectedCountry = (Country) searchSuggestion;
                mCallbacks.onToCountrySelected(selectedCountry);
            }

            @Override
            public void onSearchAction(String currentQuery) {
                if(mSuggestionList != null && !mSuggestionList.isEmpty()) {
                    mToSearchView.clearSearchFocus();
                    Country firstCountry = mSuggestionList.get(mSuggestionList.size()-1);
                    mToSearchView.setSearchText(firstCountry.getName());
                    mCallbacks.onToCountrySelected(firstCountry);
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
