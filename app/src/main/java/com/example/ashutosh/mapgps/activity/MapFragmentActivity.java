package com.example.ashutosh.mapgps.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;


import com.example.ashutosh.mapgps.R;
import com.example.ashutosh.mapgps.Retrofit.RestClient;
import com.example.ashutosh.mapgps.model.geocoding.GeoCodeMain;
import com.example.ashutosh.mapgps.utils.AppConstant;
import com.example.ashutosh.mapgps.utils.CommonData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MapFragmentActivity extends FragmentActivity implements OnMapReadyCallback {
    Double latitude, longitude;
    ArrayList<LatLng> markerLatLng = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        latitude = getIntent().getDoubleExtra("Latitude", 0.00);
        longitude = getIntent().getDoubleExtra("Longitude", 0.00);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        final Marker marker2 = googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("LatLongMain" + latitude));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(marker2.getPosition());
        LatLngBounds bounds = builder.build();
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 150);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                googleMap.animateCamera(cameraUpdate);
            }
        }, 3000);


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                String LatLng = latLng.latitude + "," + latLng.longitude;

                final MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng);

                PolylineOptions polylineOptions = new PolylineOptions();
                markerLatLng.add(latLng);
                polylineOptions.addAll(markerLatLng);
                googleMap.addPolyline(polylineOptions);

                RestClient.getApiService().geoCodeReverse(LatLng, AppConstant.serverKey, new Callback<GeoCodeMain>() {
                    @Override
                    public void success(GeoCodeMain geoCodeMain, Response response) {
                        CommonData.setGeoCodeMain(geoCodeMain);
                        markerOptions.title(geoCodeMain.getResults().get(0).getFormattedAddress());
                        googleMap.addMarker(markerOptions);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });
    }
}
