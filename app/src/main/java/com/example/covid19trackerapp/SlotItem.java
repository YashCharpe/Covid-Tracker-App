package com.example.covid19trackerapp;

public class SlotItem {



    private int hospitalIconIv,locationIconIv,clockIconIv,vaccineIconIv;
    private String hospitalNameTv,locationTv,timingTv,vaccineTv,feeTypeTv,ageLimit,ageLimitTv,availability,availabilityTv;

    public SlotItem(int hospitalIconIv, int locationIconIv, int clockIconIv, int vaccineIconIv, String hospitalNameTv, String locationTv, String timingTv, String vaccineTv, String feeTypeTv, String ageLimit, String ageLimitTv, String availability, String availabilityTv) {
        this.hospitalIconIv = hospitalIconIv;
        this.locationIconIv = locationIconIv;
        this.clockIconIv = clockIconIv;
        this.vaccineIconIv = vaccineIconIv;
        this.hospitalNameTv = hospitalNameTv;
        this.locationTv = locationTv;
        this.timingTv = timingTv;
        this.vaccineTv = vaccineTv;
        this.feeTypeTv = feeTypeTv;
        this.ageLimit = ageLimit;
        this.ageLimitTv = ageLimitTv;
        this.availability = availability;
        this.availabilityTv = availabilityTv;
    }

    public int getHospitalIconIv() {
        return hospitalIconIv;
    }

    public int getLocationIconIv() {
        return locationIconIv;
    }

    public int getClockIconIv() {
        return clockIconIv;
    }

    public int getVaccineIconIv() {
        return vaccineIconIv;
    }

    public String getHospitalNameTv() {
        return hospitalNameTv;
    }

    public String getLocationTv() {
        return locationTv;
    }

    public String getTimingTv() {
        return timingTv;
    }

    public String getVaccineTv() {
        return vaccineTv;
    }

    public String getFeeTypeTv() {
        return feeTypeTv;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public String getAgeLimitTv() {
        return ageLimitTv;
    }

    public String getAvailability() {
        return availability;
    }

    public String getAvailabilityTv() {
        return availabilityTv;
    }

    public void setHospitalIconIv(int hospitalIconIv) {
        this.hospitalIconIv = hospitalIconIv;
    }

    public void setLocationIconIv(int locationIconIv) {
        this.locationIconIv = locationIconIv;
    }

    public void setClockIconIv(int clockIconIv) {
        this.clockIconIv = clockIconIv;
    }

    public void setVaccineIconIv(int vaccineIconIv) {
        this.vaccineIconIv = vaccineIconIv;
    }

    public void setHospitalNameTv(String hospitalNameTv) {
        this.hospitalNameTv = hospitalNameTv;
    }

    public void setLocationTv(String locationTv) {
        this.locationTv = locationTv;
    }

    public void setTimingTv(String timingTv) {
        this.timingTv = timingTv;
    }

    public void setVaccineTv(String vaccineTv) {
        this.vaccineTv = vaccineTv;
    }

    public void setFeeTypeTv(String feeTypeTv) {
        this.feeTypeTv = feeTypeTv;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public void setAgeLimitTv(String ageLimitTv) {
        this.ageLimitTv = ageLimitTv;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setAvailabilityTv(String availabilityTv) {
        this.availabilityTv = availabilityTv;
    }
}
