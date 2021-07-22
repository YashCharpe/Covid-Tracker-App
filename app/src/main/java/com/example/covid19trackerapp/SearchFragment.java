package com.example.covid19trackerapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    Activity referenceActivity;
    View parentHolder;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        referenceActivity=getActivity();

        parentHolder = inflater.inflate(R.layout.search_fragment,container,false);

        ListView listView = parentHolder.findViewById(R.id.listView);
        SearchView searchView =parentHolder.findViewById(R.id.searchView);

        List<CountryPost> countryPostList = new ArrayList<>();

        CountryAdapter adapter = new CountryAdapter(getContext(),countryPostList);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://corona.lmao.ninja/v3/covid-19/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<List<CountryPost>> call = covidAPI.getCountryDetails();

        call.enqueue(new Callback<List<CountryPost>>() {
            @Override
            public void onResponse(Call<List<CountryPost>> call, Response<List<CountryPost>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(),"Error!! Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    List<CountryPost> countryPosts = response.body();

                    for(CountryPost countryPost:countryPosts)
                    {
                        String url = countryPost.getCountryInfo().getFlag();
                        countryPostList.add(countryPost);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CountryPost>> call, Throwable t) {
                Toast.makeText(getContext(),"Failure!! Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),adapter.getItem(position).getCountry(),Toast.LENGTH_SHORT).show();
            }
        });

        return parentHolder;
    }


}
