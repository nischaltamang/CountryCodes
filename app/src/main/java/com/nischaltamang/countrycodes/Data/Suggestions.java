package com.nischaltamang.countrycodes.Data;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class Suggestions {

    private static final String TAG = "Suggestions";

    public interface OnFindSuggestionsListener{
        void onResults(List<Country> results);
    }

    public static void findSuggestions(Context context, final List<Country> data, String query, final int limit, final OnFindSuggestionsListener listener){
        new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                try{
                    Thread.sleep(250);
                } catch (InterruptedException e){
                    Log.e(TAG, "", e);
                }

                List<Country> suggestionList = new ArrayList<Country>();
                if(!(constraint == null || constraint.length() == 0)){

                    for(Country country: data){

                        if(constraint.length()<=3) {
                            if (country.getBody().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                                suggestionList.add(country);

                                if (suggestionList.size() >= limit)
                                    break;
                            }
                        } else {
                            if (country.getBody().toUpperCase().contains(constraint.toString().toUpperCase())) {
                                suggestionList.add(country);

                                if (suggestionList.size() >= limit)
                                    break;
                            }
                        }

                    }
                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(listener != null){
                    listener.onResults((List<Country>) results.values);
                }
            }
        }.filter(query);
    }

}
