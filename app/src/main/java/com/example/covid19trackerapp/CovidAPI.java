package com.example.covid19trackerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidAPI {

    @GET("all")
    Call<Post> getAllDetails();

    @GET("countries")
    Call<List<CountryPost>> getCountryDetails();

    @GET("all?lastdays=all")
    Call<DateWiseStats> getDateWiseDetails();

    @GET("{countryName}")
    Call<CountryPost> getCountryWiseDetails(@Path("countryName") String countryName);

    @GET("v2/admin/location/states")
    Call<StateMainModel> getAllIndiaStates();


}
