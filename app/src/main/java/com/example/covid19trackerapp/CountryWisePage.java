package com.example.covid19trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryWisePage extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_wise_page);

        Bundle bundle = getIntent().getExtras();

        String countryName = bundle.getString("countryName");

        textView=findViewById(R.id.textView);

        textView.setText(countryName);


        LineChart lineChart = findViewById(R.id.lineChart);

        List<Entry> entries = new ArrayList<Entry>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://disease.sh/v3/covid-19/historical/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<DateWiseStats> call = covidAPI.getDateWiseDetails();

        call.enqueue(new Callback<DateWiseStats>() {
            @Override
            public void onResponse(Call<DateWiseStats> call, Response<DateWiseStats> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Error!!Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DateWiseStats dateWiseStats = response.body();
                    int i=0;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayOne(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwo(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThree(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayFour(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayFive(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayFifteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwenty(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyFive(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirty(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyFour(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyFive(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyEight(),i));
                    i++;
                    LineDataSet dataSet = new LineDataSet(entries,"Total Cases");

                    LineData lineData = new LineData(dataSet);
                    lineChart.setData(lineData);

                    lineChart.setTouchEnabled(false);
                    lineChart.setClickable(false);
                    lineChart.setDoubleTapToZoomEnabled(false);
                    lineChart.setDoubleTapToZoomEnabled(false);

                    lineChart.setDrawBorders(false);
                    lineChart.setDrawGridBackground(false);

                    dataSet.setDrawCircles(false);
                    //dataSet.setDrawCircleHole(false);

                    lineChart.getDescription().setEnabled(false);
                    lineChart.getLegend().setEnabled(false);

                    lineChart.getAxisLeft().setDrawGridLines(false);
                    lineChart.getAxisLeft().setDrawLabels(false);
                    lineChart.getAxisLeft().setDrawAxisLine(false);

                    lineChart.getXAxis().setDrawGridLines(false);
                    lineChart.getXAxis().setDrawLabels(false);
                    lineChart.getXAxis().setDrawAxisLine(false);

                    lineChart.getAxisRight().setDrawGridLines(false);
                    lineChart.getAxisRight().setDrawLabels(false);
                    lineChart.getAxisRight().setDrawAxisLine(false);

                    dataSet.setDrawValues(false);

                    lineChart.invalidate();


                }
            }

            @Override
            public void onFailure(Call<DateWiseStats> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure!!Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




//
//        List<Entry> entries = new ArrayList<Entry>();
//
//        for(int i=0;i<10;i++)
//        {
//            entries.add(new Entry(i,i));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries,"Trial Chart");
//
//        LineData lineData = new LineData(dataSet);
//        lineChart.setData(lineData);
//
//
//
//        lineChart.setTouchEnabled(false);
//        lineChart.setClickable(false);
//        lineChart.setDoubleTapToZoomEnabled(false);
//        lineChart.setDoubleTapToZoomEnabled(false);
//
//        lineChart.setDrawBorders(false);
//        lineChart.setDrawGridBackground(false);
//
//        dataSet.setDrawCircles(false);
//        //dataSet.setDrawCircleHole(false);
//
//        lineChart.getDescription().setEnabled(false);
//        lineChart.getLegend().setEnabled(false);
//
//        lineChart.getAxisLeft().setDrawGridLines(false);
//        lineChart.getAxisLeft().setDrawLabels(false);
//        lineChart.getAxisLeft().setDrawAxisLine(false);
//
//        lineChart.getXAxis().setDrawGridLines(false);
//        lineChart.getXAxis().setDrawLabels(false);
//        lineChart.getXAxis().setDrawAxisLine(false);
//
//        lineChart.getAxisRight().setDrawGridLines(false);
//        lineChart.getAxisRight().setDrawLabels(false);
//        lineChart.getAxisRight().setDrawAxisLine(false);
//
//        dataSet.setDrawValues(false);
//
//        lineChart.invalidate();
//


    }
}