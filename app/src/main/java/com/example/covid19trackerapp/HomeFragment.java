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

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jetbrains.annotations.NotNull;

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

        confirmedCaseTv=parentHolder.findViewById(R.id.confirmedCaseTv);
        activeCaseTv=parentHolder.findViewById(R.id.activeCaseTv);
        recoveredTv=parentHolder.findViewById(R.id.recoveredTv);
        deathsTv=parentHolder.findViewById(R.id.deathsTv);
        tableConfirmedCaseTv=parentHolder.findViewById(R.id.tableConfirmedCaseTv);
        tableDeathsTv=parentHolder.findViewById(R.id.tableDeathsTv);
        tableRecoveredTv=parentHolder.findViewById(R.id.tableRecoveredTv);
        tableCasesPerMillionTv=parentHolder.findViewById(R.id.tableCasesPerMillionTv);
        tableDeathsPerMillionTv=parentHolder.findViewById(R.id.tableDeathsPerMillionTv);
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
