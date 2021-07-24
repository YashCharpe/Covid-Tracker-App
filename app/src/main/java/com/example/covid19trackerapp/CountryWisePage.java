package com.example.covid19trackerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryWisePage extends AppCompatActivity {

    private TextView textView,confirmedCaseTv,criticalTv,recoveredTv,deceasedTv;
    private ImageView flagImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_wise_page);

        Toolbar mytoolbar =findViewById(R.id.mytoolbar);
        setSupportActionBar(mytoolbar);

        PieChart pieChart;

        Bundle bundle = getIntent().getExtras();

        String countryName = bundle.getString("countryName");

        flagImageView = findViewById(R.id.flagImageView);
        textView=findViewById(R.id.textView);
        confirmedCaseTv = findViewById(R.id.confirmedCaseTv);
        criticalTv = findViewById(R.id.criticalTv);
        recoveredTv = findViewById(R.id.recoveredTv);
        deceasedTv = findViewById(R.id.deceasedTv);
        pieChart = findViewById(R.id.pieChart);

        textView.setText(countryName);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://corona.lmao.ninja/v3/covid-19/countries/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<CountryPost> call = covidAPI.getCountryWiseDetails(countryName);

        call.enqueue(new Callback<CountryPost>() {
            @Override
            public void onResponse(Call<CountryPost> call, Response<CountryPost> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Error!!Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    CountryPost countryPost = response.body();

                    Glide.with(getApplicationContext()).load(countryPost.getCountryInfo().getFlag()).into(flagImageView);

                    confirmedCaseTv.setText(countryPost.getTodayCases().toString());
                    criticalTv.setText(countryPost.getCritical().toString());
                    recoveredTv.setText(countryPost.getTodayRecovered().toString());
                    deceasedTv.setText(countryPost.getTodayDeaths().toString());



                    pieChart.addPieSlice(new PieModel("Cases",countryPost.getCases(), Color.parseColor("#fed70e")));
                    pieChart.addPieSlice(new PieModel("Recovered",countryPost.getRecovered(),Color.parseColor("#399f4c")));
                    pieChart.addPieSlice(new PieModel("Deaths",countryPost.getDeaths(),Color.parseColor("#ea5568")));
                    pieChart.addPieSlice(new PieModel("Active Cases",countryPost.getActive(),Color.parseColor("#097cf3")));
                    pieChart.startAnimation();


                }

            }

            @Override
            public void onFailure(Call<CountryPost> call, Throwable t) {

            }
        });

















    }
}