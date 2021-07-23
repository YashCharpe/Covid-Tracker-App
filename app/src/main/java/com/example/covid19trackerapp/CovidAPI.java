package com.example.covid19trackerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPI {

    @GET("all")
    Call<Post> getAllDetails();

    @GET("countries")
    Call<List<CountryPost>> getCountryDetails();

    @GET("all?lastdays=all")
    Call<DateWiseStats> getDateWiseDetails();


}
