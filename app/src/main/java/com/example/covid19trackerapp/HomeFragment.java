package com.example.covid19trackerapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    Activity referenceActivity;
    View parentHolder;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        referenceActivity = getActivity();

        parentHolder = inflater.inflate(R.layout.home_fragment,container,false);

        TextView confirmedCaseTv,activeCaseTv,recoveredTv,deathsTv,tableConfirmedCaseTv,tableDeathsTv,tableRecoveredTv,tableCasesPerMillionTv,tableDeathsPerMillionTv;

        PieChart pieChart = parentHolder.findViewById(R.id.pieChart);

        LineChart lineChart = parentHolder.findViewById(R.id.lineChart);
        LineChart lineChart3 = parentHolder.findViewById(R.id.lineChart3);
        LineChart lineChart4 = parentHolder.findViewById(R.id.lineChart4);

        confirmedCaseTv=parentHolder.findViewById(R.id.confirmedCaseTv);
        activeCaseTv=parentHolder.findViewById(R.id.activeCaseTv);
        recoveredTv=parentHolder.findViewById(R.id.recoveredTv);
        deathsTv=parentHolder.findViewById(R.id.deathsTv);
        tableConfirmedCaseTv=parentHolder.findViewById(R.id.tableConfirmedCaseTv);
        tableDeathsTv=parentHolder.findViewById(R.id.tableDeathsTv);
        tableRecoveredTv=parentHolder.findViewById(R.id.tableRecoveredTv);
        tableCasesPerMillionTv=parentHolder.findViewById(R.id.tableCasesPerMillionTv);
        tableDeathsPerMillionTv=parentHolder.findViewById(R.id.tableDeathsPerMillionTv);

        List<Entry> entries = new ArrayList<Entry>();

        Retrofit retrofit1 = new Retrofit.Builder().baseUrl("https://disease.sh/v3/covid-19/historical/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI1 = retrofit1.create(CovidAPI.class);

        Call<DateWiseStats> call1 = covidAPI1.getDateWiseDetails();

        call1.enqueue(new Callback<DateWiseStats>() {
            @Override
            public void onResponse(Call<DateWiseStats> call, Response<DateWiseStats> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(),"Error!!Code: "+response.code(),Toast.LENGTH_SHORT).show();
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
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDaySix(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDaySeven(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayEight(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayNine(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTen(),i));
                    i++;

                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayEleven(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwelve(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayFourteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayFifteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDaySixteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDaySeventeen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayEighteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayNineteen(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwenty(),i));
                    i++;

                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyOne(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyTwo(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyThree(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyFour(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyFive(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentySix(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentySeven(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyEight(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayTwentyNine(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirty(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyOne(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyTwo(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyThree(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyFour(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyFive(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtySix(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtySeven(),i));
                    i++;
                    entries.add(new Entry(dateWiseStats.getCasesModel().getDayThirtyEight(),i));
                    i++;
                    LineDataSet dataSet = new LineDataSet(entries,"Total Cases");

                    LineData lineData = new LineData(dataSet);
                    lineChart.setData(lineData);

                    dataSet.setColor(Color.parseColor("#fe0c3c"));

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
                Toast.makeText(getContext(),"Failure!!Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        List<Entry> entries3 = new ArrayList<Entry>();
        Retrofit retrofit3 = new Retrofit.Builder().baseUrl("https://disease.sh/v3/covid-19/historical/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI3 = retrofit1.create(CovidAPI.class);

        Call<DateWiseStats> call3 = covidAPI3.getDateWiseDetails();

        call3.enqueue(new Callback<DateWiseStats>() {
            @Override
            public void onResponse(Call<DateWiseStats> call, Response<DateWiseStats> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(),"Error!!Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DateWiseStats dateWiseStats3 = response.body();
                    int i=0;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayOne(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwo(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThree(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayFour(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayFive(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDaySix(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDaySeven(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayEight(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayNine(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTen(),i));
                    i++;

                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayEleven(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwelve(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirteen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayFourteen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayFifteen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDaySixteen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDaySeventeen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayEighteen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayNineteen(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwenty(),i));
                    i++;

                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyOne(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyTwo(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyThree(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyFour(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyFive(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentySix(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentySeven(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyEight(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayTwentyNine(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirty(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtyOne(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtyTwo(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtyThree(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtyFour(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtyFive(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtySix(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtySeven(),i));
                    i++;
                    entries3.add(new Entry(dateWiseStats3.getRecoveredModel().getDayThirtyEight(),i));
                    i++;

                    LineDataSet dataSet3 = new LineDataSet(entries3,"Daily Deaths");

                    LineData lineData3 = new LineData(dataSet3);
                    lineChart3.setData(lineData3);

                    dataSet3.setColor(Color.parseColor("#399f4c"));

                    lineChart3.setTouchEnabled(false);
                    lineChart3.setClickable(false);
                    lineChart3.setDoubleTapToZoomEnabled(false);
                    lineChart3.setDoubleTapToZoomEnabled(false);

                    lineChart3.setDrawBorders(false);
                    lineChart3.setDrawGridBackground(false);

                    dataSet3.setDrawCircles(false);
                    //dataSet.setDrawCircleHole(false);

                    lineChart3.getDescription().setEnabled(false);
                    lineChart3.getLegend().setEnabled(false);

                    lineChart3.getAxisLeft().setDrawGridLines(false);
                    lineChart3.getAxisLeft().setDrawLabels(false);
                    lineChart3.getAxisLeft().setDrawAxisLine(false);

                    lineChart3.getXAxis().setDrawGridLines(false);
                    lineChart3.getXAxis().setDrawLabels(false);
                    lineChart3.getXAxis().setDrawAxisLine(false);

                    lineChart3.getAxisRight().setDrawGridLines(false);
                    lineChart3.getAxisRight().setDrawLabels(false);
                    lineChart3.getAxisRight().setDrawAxisLine(false);

                    dataSet3.setDrawValues(false);

                    lineChart3.invalidate();

                }
            }

            @Override
            public void onFailure(Call<DateWiseStats> call, Throwable t) {
                Toast.makeText(getContext(),"Failure!!Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });





        List<Entry> entries4 = new ArrayList<Entry>();
        Retrofit retrofit4 = new Retrofit.Builder().baseUrl("https://disease.sh/v3/covid-19/historical/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI4 = retrofit1.create(CovidAPI.class);

        Call<DateWiseStats> call4 = covidAPI4.getDateWiseDetails();

        call4.enqueue(new Callback<DateWiseStats>() {
            @Override
            public void onResponse(Call<DateWiseStats> call, Response<DateWiseStats> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(),"Error!!Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DateWiseStats dateWiseStats4 = response.body();
                    int i=0;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayOne(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwo(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThree(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayFour(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayFive(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDaySix(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDaySeven(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayEight(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayNine(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTen(),i));
                    i++;

                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayEleven(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwelve(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirteen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayFourteen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayFifteen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDaySixteen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDaySeventeen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayEighteen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayNineteen(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwenty(),i));
                    i++;

                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyOne(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyTwo(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyThree(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyFour(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyFive(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentySix(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentySeven(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyEight(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayTwentyNine(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirty(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtyOne(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtyTwo(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtyThree(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtyFour(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtyFive(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtySix(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtySeven(),i));
                    i++;
                    entries4.add(new Entry(dateWiseStats4.getDeathsModel().getDayThirtyEight(),i));
                    i++;

                    LineDataSet dataSet4 = new LineDataSet(entries4,"Daily Deaths");

                    LineData lineData4 = new LineData(dataSet4);
                    lineChart4.setData(lineData4);

                    dataSet4.setColor(Color.parseColor("#6c757c"));

                    lineChart4.setTouchEnabled(false);
                    lineChart4.setClickable(false);
                    lineChart4.setDoubleTapToZoomEnabled(false);
                    lineChart4.setDoubleTapToZoomEnabled(false);

                    lineChart4.setDrawBorders(false);
                    lineChart4.setDrawGridBackground(false);

                    dataSet4.setDrawCircles(false);
                    //dataSet.setDrawCircleHole(false);

                    lineChart4.getDescription().setEnabled(false);
                    lineChart4.getLegend().setEnabled(false);

                    lineChart4.getAxisLeft().setDrawGridLines(false);
                    lineChart4.getAxisLeft().setDrawLabels(false);
                    lineChart4.getAxisLeft().setDrawAxisLine(false);

                    lineChart4.getXAxis().setDrawGridLines(false);
                    lineChart4.getXAxis().setDrawLabels(false);
                    lineChart4.getXAxis().setDrawAxisLine(false);

                    lineChart4.getAxisRight().setDrawGridLines(false);
                    lineChart4.getAxisRight().setDrawLabels(false);
                    lineChart4.getAxisRight().setDrawAxisLine(false);

                    dataSet4.setDrawValues(false);

                    lineChart4.invalidate();

                }
            }

            @Override
            public void onFailure(Call<DateWiseStats> call, Throwable t) {
                Toast.makeText(getContext(),"Failure!!Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://corona.lmao.ninja/v3/covid-19/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<Post> call = covidAPI.getAllDetails();


        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(),"Error!!Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    Post post = response.body();

                    confirmedCaseTv.setText(String.valueOf(post.getTodayCases()));
                    activeCaseTv.setText(String.valueOf(post.getActive()));
                    recoveredTv.setText(String.valueOf(post.getTodayRecovered()));
                    deathsTv.setText(String.valueOf(post.getTodayDeaths()));
                    tableConfirmedCaseTv.setText(String.valueOf(post.getCases()));
                    tableDeathsTv.setText(String.valueOf(post.getDeaths()));
                    tableRecoveredTv.setText(String.valueOf(post.getRecovered()));
                    tableCasesPerMillionTv.setText(String.valueOf(post.getCasesPerOneMillion()));
                    tableDeathsPerMillionTv.setText(String.valueOf(post.getDeathsPerOneMillion()));

                    pieChart.addPieSlice(new PieModel("Cases",post.getCases(), Color.parseColor("#fed70e")));
                    pieChart.addPieSlice(new PieModel("Recovered",post.getRecovered(),Color.parseColor("#399f4c")));
                    pieChart.addPieSlice(new PieModel("Deaths",post.getDeaths(),Color.parseColor("#ea5568")));
                    pieChart.addPieSlice(new PieModel("Active Cases",post.getActive(),Color.parseColor("#097cf3")));
                    pieChart.startAnimation();

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getContext(),"Failure!!Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return parentHolder;
    }
}
