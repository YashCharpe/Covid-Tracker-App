package com.example.covid19trackerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryPost> implements Filterable {

    private Context context;
    private List<CountryPost> countryPostList;
    private List<CountryPost> countryPostListFiltered;


    public CountryAdapter(Context context, List<CountryPost> countryPostList) {
        super(context, R.layout.list_row, countryPostList);

        this.context=context;
        this.countryPostList=countryPostList;
        this.countryPostListFiltered = countryPostList;

    }

    @Override
    public int getCount() {
        return countryPostListFiltered.size();
    }

    @Override
    public CountryPost getItem(int position) {
        return countryPostListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null,true);

        TextView countryNameText = view.findViewById(R.id.countryNameText);
        ImageView countryFlagImage = view.findViewById(R.id.countryFlagImage);


        countryNameText.setText(countryPostList.get(position).getCountry());
        Glide.with(context).load(countryPostList.get(position).getCountryInfo().getFlag()).into(countryFlagImage);

        return view;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryPostList.size();
                    filterResults.values = countryPostList;

                }else{
                    List<CountryPost> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryPost itemsModel:countryPostList){
                        if(itemsModel.getCountry().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryPostListFiltered = (List<CountryPost>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

}

