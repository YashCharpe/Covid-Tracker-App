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
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public StaticViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
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

        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextView1.setText(currentItem.getmText1());
        holder.mTextView2.setText(currentItem.getmText2());

    }

    @Override
    public int getItemCount() {
        return mSlotList.size();
    }
}
