package com.example.ashutosh.mapgps.Retrofit;


import com.example.ashutosh.mapgps.config.Config;

import retrofit.RestAdapter;

/**
 */
public class RestClient {
    private static ApiService apiService = null;

    public static ApiService getApiService() {
        if (apiService == null) {

            // For object response which is default
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Config.getBaseURL()).setLogLevel(RestAdapter.LogLevel.FULL).build();
            apiService = restAdapter.create(ApiService.class);
        }
        return apiService;
    }
}
