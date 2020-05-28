package com.example.gps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class mLocalizacion implements LocationListener {


    @Override
    public void onLocationChanged(Location location) {
        String lat = "Latitud: " + location.getLatitude();
        String lon = "Longitud: " + location.getLongitude();



    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
