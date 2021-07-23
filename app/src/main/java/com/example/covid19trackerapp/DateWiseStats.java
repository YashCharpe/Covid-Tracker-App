package com.example.covid19trackerapp;

import com.google.gson.annotations.SerializedName;

public class DateWiseStats {

    @SerializedName("cases")
    private CasesModel casesModel;

    @SerializedName("deaths")
    private DeathsModel deathsModel;

    @SerializedName("recovered")
    private RecoveredModel recoveredModel;

    public DateWiseStats() {
    }

    public DateWiseStats(CasesModel casesModel, DeathsModel deathsModel, RecoveredModel recoveredModel) {
        this.casesModel = casesModel;
        this.deathsModel = deathsModel;
        this.recoveredModel = recoveredModel;
    }

    public CasesModel getCasesModel() {
        return casesModel;
    }

    public void setCasesModel(CasesModel casesModel) {
        this.casesModel = casesModel;
    }

    public DeathsModel getDeathsModel() {
        return deathsModel;
    }

    public void setDeathsModel(DeathsModel deathsModel) {
        this.deathsModel = deathsModel;
    }

    public RecoveredModel getRecoveredModel() {
        return recoveredModel;
    }

    public void setRecoveredModel(RecoveredModel recoveredModel) {
        this.recoveredModel = recoveredModel;
    }
}
