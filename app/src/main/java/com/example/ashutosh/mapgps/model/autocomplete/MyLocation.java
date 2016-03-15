package com.example.ashutosh.mapgps.model.autocomplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class MyLocation {
    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = new ArrayList<Prediction>();
    @SerializedName("status")
    @Expose
    private String status;


    public List<Prediction> getPredictions() {
        return predictions;
    }


    public String getStatus() {
        return status;
    }


}
