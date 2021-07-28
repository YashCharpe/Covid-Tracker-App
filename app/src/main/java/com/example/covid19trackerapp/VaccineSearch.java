package com.example.covid19trackerapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VaccineSearch extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private AutoCompleteTextView stateAutoCompleteTextView,districtAutoCompleteTextView;
    private ArrayList<String> stateList,districtList;
    private TextView tv,dateTv;
    private StateMainModel stateMainModel;
    private DistrictMainModel districtMainModel;
    private Button checkSlotBtn,selectDateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_search);

        stateAutoCompleteTextView = findViewById(R.id.stateAutoCompleteTextView);
        districtAutoCompleteTextView = findViewById(R.id.districtAutoCompleteTextView);
        tv = findViewById(R.id.tv);
        checkSlotBtn = findViewById(R.id.checkSlotBtn);
        selectDateBtn = findViewById(R.id.selectDateBtn);
        dateTv = findViewById(R.id.dateTv);

        stateList = new ArrayList<>();
        districtList = new ArrayList<>();

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,stateList);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,districtList);



        OkHttpClient client = new OkHttpClient();

        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request originalRequest = chain.request();
                Request requestWithUserAgent = originalRequest.newBuilder().header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36").build();
                return  chain.proceed(requestWithUserAgent);


            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cdn-api.co-vin.in/api/").client(client.newBuilder().build()).addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<StateMainModel> call = covidAPI.getAllIndiaStates();

        call.enqueue(new Callback<StateMainModel>() {
            @Override
            public void onResponse(Call<StateMainModel> call, Response<StateMainModel> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"State Error!!Code: "+response.code()+response.toString(),Toast.LENGTH_SHORT).show();
                    tv.setText(response.toString());
                    return;
                }
                else
                {
                    stateMainModel = response.body();

                    //Toast.makeText(getApplicationContext(),"Response: "+stateMainModel.getStates(),Toast.LENGTH_SHORT).show();

                    for(int i=0;i<stateMainModel.getStates().size();i++)
                    {
                        stateList.add(stateMainModel.getStates().get(i).getState_name());
                    }

                    stateAutoCompleteTextView.setAdapter(stateAdapter);
                }

            }

            @Override
            public void onFailure(Call<StateMainModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"State Error!! Response: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        stateAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedStateName = stateList.get(position).toString();
                int selectedStateId =0;

                for(int i=0;i<stateMainModel.getStates().size();i++)
                {
                    if(stateMainModel.getStates().get(i).getState_name().equals(selectedStateName))
                    {
                        selectedStateId = stateMainModel.getStates().get(i).getState_id();
                        break;
                    }
                }

                Toast.makeText(getApplicationContext(),selectedStateName+" "+selectedStateId,Toast.LENGTH_SHORT).show();

                Call<DistrictMainModel> call1 = covidAPI.getStateWiseDistricts(selectedStateId);

                call1.enqueue(new Callback<DistrictMainModel>() {
                    @Override
                    public void onResponse(Call<DistrictMainModel> call, Response<DistrictMainModel> response) {
                        if(!response.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"District Error!!Code: "+response.code()+response.toString(),Toast.LENGTH_SHORT).show();
                            tv.setText(response.toString());
                            return;
                        }
                        else
                        {
                            districtMainModel = response.body();
                            districtList.clear();
                            for(int i=0;i<districtMainModel.getDistricts().size();i++)
                            {
                                districtList.add(districtMainModel.getDistricts().get(i).getDistrict_name());
                            }
                            districtAutoCompleteTextView.setAdapter(districtAdapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<DistrictMainModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"District Error!! Response: "+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(),"date picker");

            }
        });

        checkSlotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                String selectedDistrict = districtAutoCompleteTextView.getText().toString();
                int selectedDistrictId = 0;
                //bundle.putString("district_id",districtAutoCompleteTextView.getText().toString());
                bundle.putString("date",dateTv.getText().toString());

                for(int i=0;i<districtMainModel.getDistricts().size();i++)
                {
                    if(districtMainModel.getDistricts().get(i).getDistrict_name().equals(selectedDistrict))
                    {
                        selectedDistrictId = districtMainModel.getDistricts().get(i).getDistrict_id();
                    }
                }
                bundle.putString("district_id", String.valueOf(selectedDistrictId));

                Intent intent = new Intent(getApplicationContext(),VaccineSlotsPage.class);
                intent.putExtras(bundle);
                startActivity(intent);

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Toast.makeText(getApplicationContext(),currentDateString,Toast.LENGTH_SHORT).show();
        dateTv.setText(dayOfMonth+"-"+(month+1)+"-"+year);
    }
}
