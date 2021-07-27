package com.example.covid19trackerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VaccineSlotsPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_slots_page);

        ArrayList<SlotItem> slotList = new ArrayList<>();


        for(int i=0;i<4;i++)
        {
            slotList.add(new SlotItem(R.drawable.ic_hospital,R.drawable.ic_location,R.drawable.ic_clock,R.drawable.ic_vaccine_bottle,"Dental Hospital Amravati","Near D-Mart, Mardi Road, Amravati","From : 09.00.00 To 17.00.00","Covishield","Free","Age Limit :","45","Availability:","100"));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SlotAdapter(slotList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}