package com.example.covid19trackerapp;

public class DistrictIdNameModel {

    private Integer district_id;
    private String district_name;

    public DistrictIdNameModel() {
    }

    public DistrictIdNameModel(Integer district_id, String district_name) {
        this.district_id = district_id;
        this.district_name = district_name;
    }

    public Integer getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Integer district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
}
