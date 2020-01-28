package com.example.dublinbikestations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


//Usama Ali Khan (17810)
// Isaac Costa Da Silva(18735)

//activity to display the station information
public class StationInfoActivity extends AppCompatActivity {

    TextView stationName,stationAddress,bikes,lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);

        stationName=findViewById(R.id.station_info_name);
        stationAddress=findViewById(R.id.station_info_address);
        bikes=findViewById(R.id.station_info_bikes);
        lat=findViewById(R.id.station_info_lat);
        lng=findViewById(R.id.station_info_lng);


        StationData data= (StationData) getIntent().getSerializableExtra("item");

        stationName.setText(data.getName());
        stationAddress.setText("Address:\n\t"+data.getAddress());
        bikes.setText("Bikes:\n\t"+data.getBikeStands());
        lat.setText("Latitude:\n\t"+data.getLat());
        lng.setText("Longitude:\n\t"+data.getLng());


    }
}
