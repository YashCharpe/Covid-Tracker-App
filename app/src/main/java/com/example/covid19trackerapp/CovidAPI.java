    package com.example.covid19trackerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("v2/admin/location/districts/{districtId}")
    Call<DistrictMainModel> getStateWiseDistricts(@Path("districtId") Integer districtId);

    @GET("v2/appointment/sessions/public/findByDistrict")
    Call<VaccineSessionModel> getVaccineSlotsByDistricts(@Query("district_id") int district_id, @Query("date") String date);

    @GET("v2/appointment/sessions/public/findByPin")
    Call<VaccineSessionModel> getVaccineSlotsByPinCode(@Query("pincode") int pincode,@Query("date") String date);

}
