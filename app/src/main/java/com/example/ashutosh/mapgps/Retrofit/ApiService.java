package com.example.ashutosh.mapgps.Retrofit;





import com.example.ashutosh.mapgps.model.autocomplete.MyLocation;
import com.example.ashutosh.mapgps.model.geocoding.GeoCodeMain;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 */
public interface ApiService {


    @POST("/place/autocomplete/json")
    public void autoComplete(@Query("input") String location, @Query("key") String key, Callback<MyLocation> callback);
    @POST("/geocode/json")
    public void geoCode(@Query("address") String placeAddress, @Query("key") String key, Callback<GeoCodeMain> callback);
    @POST("/geocode/json")
    public void geoCodeReverse(@Query("latlng") String placeAddress, @Query("key") String key, Callback<GeoCodeMain> callback);
}
