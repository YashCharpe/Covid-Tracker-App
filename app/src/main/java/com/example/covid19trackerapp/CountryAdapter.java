package com.example.covid19trackerapp;

import android.content.Context;
import android.service.controls.Control;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<CountryPost> {

    private Context context;
    private List<CountryPost> countryPostList;

    public CountryAdapter(Context context, List<CountryPost> countryPostList) {
        super(context, R.layout.list_row, countryPostList);

        this.context=context;
        this.countryPostList=countryPostList;

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
}

