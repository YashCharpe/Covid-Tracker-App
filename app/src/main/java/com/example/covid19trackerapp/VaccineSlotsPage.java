package com.example.covid19trackerapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VaccineSlotsPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private VaccineSessionModel vaccineSessionModel;
    private ProgressBar progBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_slots_page);

        Bundle bundle = getIntent().getExtras();

        int district_id = Integer.parseInt(bundle.getString("district_id"));
        String date = bundle.getString("date");

        mRecyclerView = findViewById(R.id.recyclerView);
        progBar = findViewById(R.id.progBar);

        progBar.setVisibility(View.VISIBLE);

        ArrayList<SlotItem> slotList = new ArrayList<SlotItem>();

        /*OkHttpClient client = new OkHttpClient();

        client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request originalRequest = chain.request();
                Request requestWithUserAgent = originalRequest.newBuilder().header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36").build();
                return  chain.proceed(requestWithUserAgent);


            }
        }).build();

         */


        Log.d("ID Date", "ID: "+district_id+ " Date: "+date);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://cdn-api.co-vin.in/api/").addConverterFactory(GsonConverterFactory.create()).build();

        CovidAPI covidAPI = retrofit.create(CovidAPI.class);

        Call<VaccineSessionModel> call = covidAPI.getVaccineSlotsByDistricts(district_id,date);

        call.enqueue(new Callback<VaccineSessionModel>() {
            @Override
            public void onResponse(Call<VaccineSessionModel> call, Response<VaccineSessionModel> response) {
                if(!response.isSuccessful())
                {
                    progBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Error!!Code: "+response.code()+response.toString(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    progBar.setVisibility(View.INVISIBLE);
                    vaccineSessionModel = response.body();

                    //Toast.makeText(getApplicationContext(),"Response: "+vaccineSessionModel.getSessions(),Toast.LENGTH_SHORT).show();

                    for(int i=0;i<vaccineSessionModel.getSessions().size();i++)
                    {
                        String hospitalNameTv,locationTv,timingTv,vaccineTv,feeTypeTv,ageLimit,ageLimitTv,availability,availabilityTv;
                        hospitalNameTv = vaccineSessionModel.getSessions().get(i).getName();
                        locationTv = vaccineSessionModel.getSessions().get(i).getAddress();
                        timingTv = "From : "+vaccineSessionModel.getSessions().get(i).getFrom()+" To "+vaccineSessionModel.getSessions().get(i).getTo();
                        vaccineTv = vaccineSessionModel.getSessions().get(i).getVaccine();
                        feeTypeTv = vaccineSessionModel.getSessions().get(i).getFee_type();
                        ageLimit= "Age Limit";
                        ageLimitTv = String.valueOf(vaccineSessionModel.getSessions().get(i).getMin_age_limit());
                        availability = "Availability";
                        availabilityTv = String.valueOf(vaccineSessionModel.getSessions().get(i).getAvailable_capacity());

                        Log.d("JJJKJKJKJKJK", "hospitalNameTv: "+hospitalNameTv+"\n locationTv: "+locationTv+"\n timingTv: "+timingTv+"\n vaccineTv: "+vaccineTv+"\n feeTypeTv: "+feeTypeTv+"\n ageLimit: "+ageLimit+"\n ageLimitTv: "+ageLimitTv+"\n availabilityTv"+availabilityTv);

                        //slotList.add(new SlotItem(R.drawable.ic_hospital,R.drawable.ic_location,R.drawable.ic_clock,R.drawable.ic_vaccine_bottle,"Dental Hospital Amravati","Near D-Mart, Mardi Road, Amravati","From : 09.00.00 To 17.00.00","Covishield","Free","Age Limit :","45","Availability:","100"));
                        slotList.add(new SlotItem(R.drawable.ic_hospital,R.drawable.ic_location,R.drawable.ic_clock,R.drawable.ic_vaccine_bottle,vaccineSessionModel.getSessions().get(i).getName(),vaccineSessionModel.getSessions().get(i).getAddress(),"From : "+vaccineSessionModel.getSessions().get(i).getFrom()+" To "+vaccineSessionModel.getSessions().get(i).getTo(),vaccineSessionModel.getSessions().get(i).getVaccine(),vaccineSessionModel.getSessions().get(i).getFee_type(),"Age Limit: ",vaccineSessionModel.getSessions().get(i).getMin_age_limit().toString(),"Availability: ",vaccineSessionModel.getSessions().get(i).getAvailable_capacity().toString()));
                    }
                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mAdapter = new SlotAdapter(slotList);

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<VaccineSessionModel> call, Throwable t) {
                progBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Error!! Response: "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });




        /*for(int i=0;i<4;i++)
        {
            slotList.add(new SlotItem(R.drawable.ic_hospital,R.drawable.ic_location,R.drawable.ic_clock,R.drawable.ic_vaccine_bottle,"Dental Hospital Amravati","Near D-Mart, Mardi Road, Amravati","From : 09.00.00 To 17.00.00","Covishield","Free","Age Limit :","45","Availability:","100"));
        }*/




    }
}