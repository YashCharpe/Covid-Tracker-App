package com.example.covid19trackerapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VaccineCenterModel {

    private Integer center_id;
    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private String block_name;
    private Integer pincode;
    private String from;
    private String to;
    private Double lat;

    @SerializedName("long")
    private Double longT;

    private String fee_type;
    private String session_id;
    private String date;
    private Integer available_capacity;
    private Integer available_capacity_dose1;
    private Integer available_capacity_dose2;
    private Double fee;
    private Integer min_age_limit;
    private String vaccine;
    private List<String> slots;

    public VaccineCenterModel() {
    }

    public VaccineCenterModel(Integer center_id, String name, String address, String state_name, String district_name, String block_name, Integer pincode, String from, String to, Double lat, Double longT, String fee_type, String session_id, String date, Integer available_capacity, Integer available_capacity_dose1, Integer available_capacity_dose2, Double fee, Integer min_age_limit, String vaccine, List<String> slots) {
        this.center_id = center_id;
        this.name = name;
        this.address = address;
        this.state_name = state_name;
        this.district_name = district_name;
        this.block_name = block_name;
        this.pincode = pincode;
        this.from = from;
        this.to = to;
        this.lat = lat;
        this.longT = longT;
        this.fee_type = fee_type;
        this.session_id = session_id;
        this.date = date;
        this.available_capacity = available_capacity;
        this.available_capacity_dose1 = available_capacity_dose1;
        this.available_capacity_dose2 = available_capacity_dose2;
        this.fee = fee;
        this.min_age_limit = min_age_limit;
        this.vaccine = vaccine;
        this.slots = slots;
    }

    public Integer getCenter_id() {
        return center_id;
    }

    public void setCenter_id(Integer center_id) {
        this.center_id = center_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongT() {
        return longT;
    }

    public void setLongT(Double longT) {
        this.longT = longT;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAvailable_capacity() {
        return available_capacity;
    }

    public void setAvailable_capacity(Integer available_capacity) {
        this.available_capacity = available_capacity;
    }

    public Integer getAvailable_capacity_dose1() {
        return available_capacity_dose1;
    }

    public void setAvailable_capacity_dose1(Integer available_capacity_dose1) {
        this.available_capacity_dose1 = available_capacity_dose1;
    }

    public Integer getAvailable_capacity_dose2() {
        return available_capacity_dose2;
    }

    public void setAvailable_capacity_dose2(Integer available_capacity_dose2) {
        this.available_capacity_dose2 = available_capacity_dose2;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getMin_age_limit() {
        return min_age_limit;
    }

    public void setMin_age_limit(Integer min_age_limit) {
        this.min_age_limit = min_age_limit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }
}
