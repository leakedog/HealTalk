package com.example.firstprojecttry.Logic;

public class Location{
    private Double lat, lng;
    public Location() {
        lat = lng = 0.;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}