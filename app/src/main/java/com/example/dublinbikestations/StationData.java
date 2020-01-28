package com.example.dublinbikestations;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



//Usama Ali Khan (17810)
// Isaac Costa Da Silva(18735)

public class StationData  implements  Serializable{

    private String name ;
    private String address;
    private int bikeStands;
    private double lat;
    private double lng;


    public StationData() {
    }

    public StationData(String name, String address, int bikeStands, double lat, double lng) {
        this.name = name;
        this.address = address;
        this.bikeStands = bikeStands;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBikeStands() {
        return bikeStands;
    }

    public void setBikeStands(int bikeStands) {
        this.bikeStands = bikeStands;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


}
