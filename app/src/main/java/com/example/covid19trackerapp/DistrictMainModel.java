package com.example.covid19trackerapp;

import java.util.List;

public class DistrictMainModel {

    private List<DistrictIdNameModel> districts;
    private Integer ttl;

    public DistrictMainModel() {
    }

    public DistrictMainModel(List<DistrictIdNameModel> districts, Integer ttl) {
        this.districts = districts;
        this.ttl = ttl;
    }

    public List<DistrictIdNameModel> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictIdNameModel> districts) {
        this.districts = districts;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
