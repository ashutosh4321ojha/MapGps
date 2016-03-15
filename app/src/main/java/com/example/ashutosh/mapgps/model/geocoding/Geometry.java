
package com.example.ashutosh.mapgps.model.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("bounds")
    @Expose
    private Bounds bounds;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


 //inner class
    public class Bounds {

        @SerializedName("northeast")
        @Expose
        private Northeast northeast;
        @SerializedName("southwest")
        @Expose
        private Southwest southwest;

     //inner class

     public class Northeast {

            @SerializedName("lat")
            @Expose
            private Double lat;
            @SerializedName("lng")
            @Expose
            private Double lng;

        }
     //inner class

        public class Southwest {

            @SerializedName("lat")
            @Expose
            private Double lat;
            @SerializedName("lng")
            @Expose
            private Double lng;


        }
 }

    //inner class

    public class Location {

        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;

        public Double getLat() {
            return lat;
        }

        public Double getLng() {
            return lng;
        }



    }

    //inner class

    public class Viewport {

        @SerializedName("northeast")
        @Expose
        private Northeast_ northeast;
        @SerializedName("southwest")
        @Expose
        private Southwest_ southwest;

        //inner class
        public class Northeast_ {

            @SerializedName("lat")
            @Expose
            private Double lat;
            @SerializedName("lng")
            @Expose
            private Double lng;

        }
        //inner class

        public class Southwest_ {

            @SerializedName("lat")
            @Expose
            private Double lat;
            @SerializedName("lng")
            @Expose
            private Double lng;

        }



    }



}
