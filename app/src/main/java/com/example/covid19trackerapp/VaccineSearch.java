package com.example.covid19trackerapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

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
    private TextView tv,cowinTv;
    private StateMainModel stateMainModel;
    private DistrictMainModel districtMainModel;
    private Button checkSlotBtn;
    private ProgressBar progBar;
    private TextInputLayout pincodeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_search);

        stateAutoCompleteTextView = findViewById(R.id.stateAutoCompleteTextView);
        districtAutoCompleteTextView = findViewById(R.id.districtAutoCompleteTextView);
        tv = findViewById(R.id.tv);
        checkSlotBtn = findViewById(R.id.checkSlotBtn);
        cowinTv = findViewById(R.id.cowinTv);
        progBar = findViewById(R.id.progBar);
        pincodeTv = findViewById(R.id.pincodeTv);

        progBar.setVisibility(View.VISIBLE);

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
                    progBar.setVisibility(View.INVISIBLE);
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


        checkSlotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(stateAutoCompleteTextView.getText().toString().equals("Select State")&&pincodeTv.getEditText().getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Select Your State!",Toast.LENGTH_SHORT).show();
                    stateAutoCompleteTextView.setFocusable(true);
                    stateAutoCompleteTextView.requestFocus(1);
                }
                else if (!stateAutoCompleteTextView.getText().toString().equals("Select State")&&districtAutoCompleteTextView.getText().toString().equals("Select District"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select Your District!",Toast.LENGTH_SHORT).show();
                    districtAutoCompleteTextView.setFocusable(true);
                }
                else if(!stateAutoCompleteTextView.getText().toString().equals("Select State")&&!districtAutoCompleteTextView.getText().toString().equals("Select District"))
                {
                    DialogFragment datepicker = new DatePickerFragment();
                    datepicker.show(getSupportFragmentManager(),"date picker");
                }
                else if(!pincodeTv.getEditText().getText().toString().isEmpty())
                {
                    DialogFragment datepicker = new DatePickerFragment();
                    datepicker.show(getSupportFragmentManager(),"date picker");
                }


            }
        });

        cowinTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.cowin.gov.in/"));
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        Toast.makeText(getApplicationContext(),currentDateString,Toast.LENGTH_SHORT).show();

        String selectedDate = dayOfMonth+"-"+(month+1)+"-"+year;

        if(!pincodeTv.getEditText().getText().toString().isEmpty()&&!stateAutoCompleteTextView.getText().toString().equals("Select State")&&!districtAutoCompleteTextView.getText().toString().equals("Select District"))
        {
            Bundle bundle = new Bundle();

            String selectedDistrict = districtAutoCompleteTextView.getText().toString();
            int selectedDistrictId = 0;
            bundle.putString("date",selectedDate);

            for(int i=0;i<districtMainModel.getDistricts().size();i++)
            {
                if(districtMainModel.getDistricts().get(i).getDistrict_name().equals(selectedDistrict))
                {
                    selectedDistrictId = districtMainModel.getDistricts().get(i).getDistrict_id();
                }
            }
            bundle.putString("district_id", String.valueOf(selectedDistrictId));
            bundle.putString("pincode","0");

            Intent intent = new Intent(getApplicationContext(),VaccineSlotsPage.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        else if (pincodeTv.getEditText().getText().toString().isEmpty()&&!stateAutoCompleteTextView.getText().toString().equals("Select State")&&!districtAutoCompleteTextView.getText().toString().equals("Select District"))
        {
            Bundle bundle = new Bundle();

            String selectedDistrict = districtAutoCompleteTextView.getText().toString();
            int selectedDistrictId = 0;
            bundle.putString("date",selectedDate);

            for(int i=0;i<districtMainModel.getDistricts().size();i++)
            {
                if(districtMainModel.getDistricts().get(i).getDistrict_name().equals(selectedDistrict))
                {
                    selectedDistrictId = districtMainModel.getDistricts().get(i).getDistrict_id();
                }
            }
            bundle.putString("district_id", String.valueOf(selectedDistrictId));
            bundle.putString("pincode","0");

            Intent intent = new Intent(getApplicationContext(),VaccineSlotsPage.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        else
        {
            Bundle bundle = new Bundle();
            String selectedDistrict = districtAutoCompleteTextView.getText().toString();
            int selectedDistrictId = 0;
            bundle.putString("date",selectedDate);
            bundle.putString("district_id","0");
            bundle.putString("pincode",pincodeTv.getEditText().getText().toString());

            Intent intent = new Intent(getApplicationContext(),VaccineSlotsPage.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }





    }
}
