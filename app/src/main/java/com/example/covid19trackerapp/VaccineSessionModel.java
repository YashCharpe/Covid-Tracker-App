package com.example.covid19trackerapp;

import java.util.List;

public class VaccineSessionModel {

    private List<VaccineCenterModel> sessions ;

    public VaccineSessionModel() {
    }

    public List<VaccineCenterModel> getSessions() {
        return sessions;
    }

    public void setSessions(List<VaccineCenterModel> sessions) {
        this.sessions = sessions;
    }
}
