package com.example.ashutosh.mapgps.services;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 *
 */
public class TrackingServices extends IntentService implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    LocationRequest locationRequest;
    LocationManager locationManager;
    GoogleApiClient mgoogleApiClient;
    double newLat, newLng;
    Boolean firstTime;

    public TrackingServices() {
        super("Track");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("TAGservice", "Service started");
        createLocationRequest();
        return super.onStartCommand(intent, flags, startId);
    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mgoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        mgoogleApiClient.connect();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Log.v("TAGService", String.valueOf((LocationServices.FusedLocationApi
                .requestLocationUpdates(mgoogleApiClient, locationRequest, this))));
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    @Override
    public void onLocationChanged(Location location) {
        newLat = location.getLatitude();
        newLng = location.getLongitude();
        Intent locationIntent = new Intent(LOCATION_SERVICE);
        locationIntent.putExtra("lat", newLat);
        locationIntent.putExtra("lng", newLng);
        sendBroadcast(locationIntent);
        Log.v("TAGservice", String.valueOf(newLat));
        Log.v("TAGservice", String.valueOf(newLng));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}