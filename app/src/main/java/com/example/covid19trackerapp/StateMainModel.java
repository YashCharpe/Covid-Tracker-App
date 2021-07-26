package com.example.covid19trackerapp;

import java.util.List;

public class StateMainModel {

    private List<StateIdNameModel> states;
    private Integer ttl;

    public StateMainModel() {
    }

    public StateMainModel(List<StateIdNameModel> states,Integer ttl) {
        this.states = states;
        this.ttl = ttl;
    }

    public List<StateIdNameModel> getStates() {
        return states;
    }

    public void setStates(List<StateIdNameModel> states) {
        this.states = states;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
