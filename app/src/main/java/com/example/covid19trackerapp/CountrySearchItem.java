package com.example.covid19trackerapp;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class CountrySearchItem {

    int flagImage;
    String countryName;

    public CountrySearchItem() {
    }

    public CountrySearchItem(int flagImage, String countryName) {
        this.flagImage = flagImage;
        this.countryName = countryName;
    }

    public CountrySearchItem(Bitmap drawable1, String country) {
    }

    public int getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(int flagImage) {
        this.flagImage = flagImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
