package com.example.covid19trackerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class VaccineFragment extends Fragment {

    Activity referenceActivity;
    View parentHolder;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        referenceActivity=getActivity();

        parentHolder = inflater.inflate(R.layout.vaccine_fragment,container,false);

        Button slotBtn = parentHolder.findViewById(R.id.slotBtn);

        slotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),VaccineSearch.class));
            }
        });

        return parentHolder;
    }
}
