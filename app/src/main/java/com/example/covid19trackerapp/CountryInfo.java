package com.example.covid19trackerapp;

import com.google.gson.annotations.SerializedName;

public class CountryInfo {

    private Long _id;
    private String iso2;
    private String iso3;
    private Double lat;

    @SerializedName("long")
    private Double longNo;

    private String flag;

    public CountryInfo() {
    }

    public CountryInfo(Long _id, String iso2, String iso3, Double lat, Double longNo, String flag) {
        this._id = _id;
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.lat = lat;
        this.longNo = longNo;
        this.flag = flag;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongNo() {
        return longNo;
    }

    public void setLongNo(Double longNo) {
        this.longNo = longNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
