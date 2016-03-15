
package com.example.ashutosh.mapgps.model.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GeoCodeMain {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    private String status;


    public List<Result> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }



    public class Result {

        @SerializedName("address_components")
        @Expose
        private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
        @SerializedName("formatted_address")
        @Expose
        private String formattedAddress;
        @SerializedName("geometry")
        @Expose
        private Geometry geometry;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<String>();


        public List<AddressComponent> getAddressComponents() {
            return addressComponents;
        }


        public String getFormattedAddress() {
            return formattedAddress;
        }

        public Geometry getGeometry() {
            return geometry;
        }


        public List<String> getTypes() {
            return types;
        }


//inner class
        public class AddressComponent {

            @SerializedName("long_name")
            @Expose
            private String longName;
            @SerializedName("short_name")
            @Expose
            private String shortName;
            @SerializedName("types")
            @Expose
            private List<String> types = new ArrayList<String>();

        }



    }


}
