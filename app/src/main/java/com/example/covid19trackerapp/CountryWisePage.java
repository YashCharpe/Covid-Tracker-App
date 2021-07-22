package com.example.covid19trackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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






    }
}