package com.danidu.coronatrackingsite.models;

public class LocationStats {
    private String country;
    private String state;
    private int latestTotalCases;
    private int previousCases;
    private int differFromPrev;



    @Override
    public String toString() {
        return "LocationStats{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getDifferFromPrev() {
        return differFromPrev;
    }

    public void setDifferFromPrev(int differFromPrev) {
        this.differFromPrev = differFromPrev;
    }

    public int getPreviousCases() {
        return previousCases;
    }

    public void setPreviousCases(int previousCases) {
        this.previousCases = previousCases;
    }
}
