package com.example.covid19trackerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllStatsPage extends AppCompatActivity {

    private TextView confirmedCaseTv,activeCaseTv,recoveredTv,deathsTv,tableConfirmedCaseTv,tableDeathsTv,tableRecoveredTv,tableCasesPerMillionTv,tableDeathsPerMillionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stats_page);

        BottomNavigationView bottom_navigation=findViewById(R.id.bottom_navigation);

        confirmedCaseTv=findViewById(R.id.confirmedCaseTv);
        activeCaseTv=findViewById(R.id.activeCaseTv);
        recoveredTv=findViewById(R.id.recoveredTv);
        deathsTv=findViewById(R.id.deathsTv);
        tableConfirmedCaseTv=findViewById(R.id.tableConfirmedCaseTv);
        tableDeathsTv=findViewById(R.id.tableDeathsTv);
        tableRecoveredTv=findViewById(R.id.tableRecoveredTv);
        tableCasesPerMillionTv=findViewById(R.id.tableCasesPerMillionTv);
        tableDeathsPerMillionTv=findViewById(R.id.tableDeathsPerMillionTv);
/*
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://corona.lmao.ninja/v3/covid-19/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<Post> call = covidAPI.getAllDetails();


        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Error!!Code: "+response.message(),Toast.LENGTH_SHORT).show();
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
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure!!Code: "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });*/

        bottom_navigation.setOnNavigationItemSelectedListener(navlistner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId())
            {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_search:
                    selectedFragment = new SearchFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };

}