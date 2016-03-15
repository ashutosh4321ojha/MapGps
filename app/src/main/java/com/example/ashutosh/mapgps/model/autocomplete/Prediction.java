package com.example.ashutosh.mapgps.model.autocomplete;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Prediction {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("matched_substrings")
    @Expose
    private List<MatchedSubstring> matchedSubstrings = new ArrayList<MatchedSubstring>();
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("terms")
    @Expose
    private List<Term> terms = new ArrayList<Term>();
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();


    public String getDescription() {
        return description;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }
//inner class

    public class MatchedSubstring {
        @SerializedName("length")
        @Expose
        private Integer length;
        @SerializedName("offset")
        @Expose
        private Integer offset;


    }
//inner class
    public class Term {
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("value")
        @Expose
        private String value;



    }



}

