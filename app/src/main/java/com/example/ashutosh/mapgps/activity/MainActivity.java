package com.example.ashutosh.mapgps.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ashutosh.mapgps.R;
import com.example.ashutosh.mapgps.Retrofit.RestClient;
import com.example.ashutosh.mapgps.adapter.RecyclerAdapterUserData;


import com.example.ashutosh.mapgps.model.autocomplete.MyLocation;
import com.example.ashutosh.mapgps.model.autocomplete.Prediction;
import com.example.ashutosh.mapgps.model.geocoding.GeoCodeMain;
import com.example.ashutosh.mapgps.services.TrackingServices;
import com.example.ashutosh.mapgps.utils.AppConstant;
import com.example.ashutosh.mapgps.utils.CommonData;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ashutosh on 2/29/2016.
 */
public class MainActivity extends AppCompatActivity implements AppConstant {
    public String place;
    AutoCompleteTextView autoCompleteTextView;
    TextWatcher textWatcher;
    TextView tvPlaceLocation;
    Intent intent;
    Button btClickSeeMap, btGetLocation;
    private double latForGPS, lngForGPS;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapterUserData adapter;
    List<GeoCodeMain.Result> listResult;
    List<Prediction> list;
    ProgressDialog progressDialog;

    void init() {

        initTextWatcher();

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.tv_location);
        //   tvPlaceLocation = (TextView) findViewById(R.id.tv_address);
        btClickSeeMap = (Button) findViewById(R.id.bt_map);
        btGetLocation = (Button) findViewById(R.id.bt_gps);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_list);
        layoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        init();

        autoCompleteTextView.addTextChangedListener(textWatcher);

        intent = new Intent(MainActivity.this, TrackingServices.class);
        startService(intent);

        MyReciever myReceiver = new MyReciever();
        registerReceiver(myReceiver, new IntentFilter(LOCATION_SERVICE));

        String place = getIntent().getStringExtra("place");
        autoCompleteTextView.setText(place);

        btClickSeeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackGeocoding();
            }
        });
        btGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GpsLocation();
            }
        });
    }

    private void GpsLocation() {
        intent = new Intent(MainActivity.this, MapFragmentActivity.class);
        intent.putExtra("Latitude", latForGPS);
        intent.putExtra("Longitude", lngForGPS);
        startActivity(intent);
    }

    private void callBackGeocoding() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("wait...");
        progressDialog.show();
        String getAddress = autoCompleteTextView.getText().toString();
        if (getAddress.equals(""))
            Toast.makeText(MainActivity.this, "Please fill the location", Toast.LENGTH_SHORT).show();
        else {
            RestClient.getApiService().geoCode(getAddress, serverKey, new Callback<GeoCodeMain>() {
                @Override
                public void success(GeoCodeMain geoCodeMain, Response response) {
                    progressDialog.dismiss();
                    CommonData.setGeoCodeMain(geoCodeMain);
                    Log.v("TAG", String.valueOf(geoCodeMain.getResults().get(0).getGeometry().getLocation().getLat()));
                    double latitude = geoCodeMain.getResults().get(0).getGeometry().getLocation().getLat();
                    double longitude = geoCodeMain.getResults().get(0).getGeometry().getLocation().getLng();
                    intent = new Intent(MainActivity.this, MapFragmentActivity.class);
                    intent.putExtra("Latitude", latitude);
                    intent.putExtra("Longitude", longitude);
                    startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.dismiss();

                }
            });
        }

    }

    private void initTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                Handler handlerTimer = new Handler();
                handlerTimer.postDelayed(new Runnable() {
                    public void run() {
                        callBackForAutoComplete(s);

                    }
                }, 100);


            }
        };
    }

    private void callBackForAutoComplete(Editable s) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("wait...");
        //  progressDialog.show();

        RestClient.getApiService().autoComplete(autoCompleteTextView.getText().toString(), serverKeyNEW, new Callback<MyLocation>() {
            @Override
            public void success(MyLocation location, Response response) {
                CommonData.setMyLocation(location);

                list = CommonData.getMyLocation().getPredictions();
                progressDialog.dismiss();

                adapter = new RecyclerAdapterUserData(MainActivity.this, list);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("failed", error.getLocalizedMessage());
                progressDialog.dismiss();

            }
        });
    }

    private class MyReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("TAG", "inRecieve");
            Bundle extras = intent.getExtras();
            latForGPS = extras.getDouble("lat");
            lngForGPS = extras.getDouble("lng");
            Log.v("TAGGPS", latForGPS + lngForGPS + "");
        }
    }
}
