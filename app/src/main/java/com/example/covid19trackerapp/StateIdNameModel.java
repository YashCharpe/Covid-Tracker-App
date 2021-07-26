package com.example.covid19trackerapp;

public class StateIdNameModel {

    private Integer state_id;
    private String state_name;

    public StateIdNameModel() {
    }

    public StateIdNameModel(Integer state_id, String state_name) {
        this.state_id = state_id;
        this.state_name = state_name;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
}
