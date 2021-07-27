package com.example.covid19trackerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.StaticViewHolder> {

    private ArrayList<SlotItem> mSlotList;

    public static class StaticViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView hospitalIconIv,locationIconIv,clockIconIv,vaccineIconIv;
        public TextView hospitalNameTv,locationTv,timingTv,vaccineTv,feeTypeTv,ageLimit,ageLimitTv,availability,availabilityTv;



        public StaticViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            hospitalIconIv =itemView.findViewById(R.id.hospitalIconIv);
            locationIconIv = itemView.findViewById(R.id.locationIconIv);
            clockIconIv = itemView.findViewById(R.id.clockIconIv);
            vaccineIconIv = itemView.findViewById(R.id.vaccineIconIv);
            hospitalNameTv = itemView.findViewById(R.id.hospitalNameTv);
            locationTv = itemView.findViewById(R.id.locationTv);
            timingTv = itemView.findViewById(R.id.timingTv);
            vaccineTv = itemView.findViewById(R.id.vaccineTv);
            feeTypeTv = itemView.findViewById(R.id.feeTypeTv);
            ageLimit = itemView.findViewById(R.id.ageLimit);
            ageLimitTv = itemView.findViewById(R.id.ageLimitTv);
            availability = itemView.findViewById(R.id.availability);
            availabilityTv = itemView.findViewById(R.id.availabilityTv);

        }
    }

    public SlotAdapter(ArrayList<SlotItem> slotList) {
        mSlotList=slotList;
    }

    @NonNull
    @NotNull
    @Override
    public StaticViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_item,parent,false);
        StaticViewHolder svh = new StaticViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SlotAdapter.StaticViewHolder holder, int position) {
        SlotItem currentItem = mSlotList.get(position);


        holder.hospitalIconIv.setImageResource(currentItem.getHospitalIconIv());
        holder.locationIconIv.setImageResource(currentItem.getLocationIconIv());
        holder.clockIconIv.setImageResource(currentItem.getClockIconIv());
        holder.vaccineIconIv.setImageResource(currentItem.getVaccineIconIv());

        holder.hospitalNameTv.setText(currentItem.getHospitalNameTv());
        holder.locationTv.setText(currentItem.getLocationTv());
        holder.timingTv.setText(currentItem.getTimingTv());
        holder.vaccineTv.setText(currentItem.getVaccineTv());
        holder.feeTypeTv.setText(currentItem.getFeeTypeTv());
        holder.ageLimit.setText(currentItem.getAgeLimit());
        holder.ageLimitTv.setText(currentItem.getAgeLimitTv());
        holder.availability.setText(currentItem.getAvailability());
        holder.availabilityTv.setText(currentItem.getAvailabilityTv());

    }

    @Override
    public int getItemCount() {
        return mSlotList.size();
    }
}
