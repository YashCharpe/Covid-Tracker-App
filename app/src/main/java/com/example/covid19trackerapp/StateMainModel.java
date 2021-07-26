package com.example.covid19trackerapp;

import java.util.ArrayList;

public class StateMainModel {

    private ArrayList<StateIdNameModel> states;
    private Integer ttl;

    public StateMainModel() {
    }

    public StateMainModel(ArrayList<StateIdNameModel> states,Integer ttl) {
        this.states = states;
        this.ttl = ttl;
    }

    public ArrayList<StateIdNameModel> getStates() {
        return states;
    }

    public void setStates(ArrayList<StateIdNameModel> states) {
        this.states = states;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
