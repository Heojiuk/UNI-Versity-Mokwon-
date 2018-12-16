package com.example.heoju.smartdv.AR.model;

import android.location.Location;



public class ARPoint {
    Location location;
    String name;

//현재 위도 경도 고도를 location에 셋팅함
    public ARPoint(String name, double lat, double lon, double altitude) {
        this.name = name;
        location = new Location("ARPoint");
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setAltitude(altitude);
    }



    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
