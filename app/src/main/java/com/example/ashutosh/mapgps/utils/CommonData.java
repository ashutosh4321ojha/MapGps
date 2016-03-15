package com.example.ashutosh.mapgps.utils;


import com.example.ashutosh.mapgps.model.autocomplete.MyLocation;
import com.example.ashutosh.mapgps.model.geocoding.GeoCodeMain;

/**
 */
public class CommonData {

    private static MyLocation myLocation = null;

    private static GeoCodeMain geoCodeMain = null;

    public static MyLocation getMyLocation() {
        return myLocation;
    }

    public static void setMyLocation(MyLocation myLocation) {
        CommonData.myLocation = myLocation;
    }


    public static GeoCodeMain getGeoCodeMain() {
        return geoCodeMain;
    }

    public static void setGeoCodeMain(GeoCodeMain geoCodeMain) {
        CommonData.geoCodeMain = geoCodeMain;
    }

}
