package com.example.covid19trackerapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VaccineSearch extends AppCompatActivity {

    private AutoCompleteTextView stateAutoCompleteTextView,districtAutoCompleteTextView;
    private ArrayList<String> stateList,districtList;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_search);

        stateAutoCompleteTextView = findViewById(R.id.stateAutoCompleteTextView);
        districtAutoCompleteTextView = findViewById(R.id.districtAutoCompleteTextView);
        tv = findViewById(R.id.tv);

        stateList = new ArrayList<>();

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,stateList);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cdn-api.co-vin.in/api/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<StateMainModel> call = covidAPI.getAllIndiaStates();

        call.enqueue(new Callback<StateMainModel>() {
            @Override
            public void onResponse(Call<StateMainModel> call, Response<StateMainModel> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Error!!Code: "+response.code()+response.toString(),Toast.LENGTH_SHORT).show();
                    tv.setText(response.toString());
                    return;
                }
                else
                {
                    StateMainModel stateMainModel = response.body();

                    int size = stateMainModel.getStates().size();

                    for(int i=0;i<size;i++)
                    {
                        stateList.add(stateMainModel.getStates().get(i).getState_name());
                    }

                    stateAutoCompleteTextView.setAdapter(stateAdapter);
                }

            }

            @Override
            public void onFailure(Call<StateMainModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error!! Response: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



        /*stateList = new ArrayList<>();
        districtList = new ArrayList<>();

        stateList.add("Maharashtra");
        stateList.add("Madhya Pradesh");
        stateList.add("Goa");
        stateList.add("Rajasthan");

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,stateList);

        stateAutoCompleteTextView.setAdapter(stateAdapter);

        stateAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(stateList.get(position).toString().equals("Maharashtra"))
                {
                    districtList.clear();
                    districtList.add("Amravati");
                    districtList.add("Pune");
                    districtList.add("Mumbai");
                }
                else if(stateList.get(position).equals("Rajasthan"))
                {
                    districtList.clear();
                    districtList.add("Jaipur");
                    districtList.add("Jodhpur");
                }
            }
        });

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,districtList);


        districtAutoCompleteTextView.setAdapter(districtAdapter);

         */

    }
}