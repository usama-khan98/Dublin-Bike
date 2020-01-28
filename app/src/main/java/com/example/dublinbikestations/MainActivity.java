package com.example.dublinbikestations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//Usama Ali Khan (17810)
// Isaac Costa Da Silva(18735)


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private Button listBtn;
    public static List<StationData> stationsList;
    private SupportMapFragment supportMapFragment;


    int done =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button to navigate to stationsList screen
        listBtn = findViewById(R.id.list_btn);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(getApplicationContext(),StationsListActivity.class);
                startActivity(myIntent);
            }
        });
        stationsList=new ArrayList<>();

        //supportFragment instance for googleMaps View
        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map );

        //Volley library to send requests
        final RequestQueue mQueue = Volley.newRequestQueue(this);

        //API url along with API key for JCDecaux
        String url = "https://api.jcdecaux.com/vls/v1/stations?contract=dublin&apiKey=814fba215ef221286fb345992da46f42064ccbb7";

            //JSON array request and responseListener to receive data from API url
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject station = response.getJSONObject(i);
                                        //JSON data parsing
                                String name = station.getString("name");
                                String address = station.getString("address");
                                int bikeStands = station.getInt("bike_stands");
                                double lat=station.getJSONObject("position").getDouble("lat");
                                double lng=station.getJSONObject("position").getDouble("lng");

                                StationData data=new StationData(name,address,bikeStands,lat,lng);
                                stationsList.add(data);
                            }

                            updateMarker();
                            //Stopping the listener from receiving response as it is asynchronous
                            mQueue.stop();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //initiating request to API
        mQueue.add(request);

    }



    public void updateMarker(){
        if(done == 0){
            //updating the markers data in Google maps view
            supportMapFragment.getMapAsync(this);
        }
        done = 1;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
//Rendering the markers in Google Maps View
        for(int i=0;i<stationsList.size()/3;i++) {
            LatLng myloc=new LatLng(stationsList.get(i).getLat(),stationsList.get(i).getLng());
            googleMap.addMarker(new MarkerOptions().position(myloc).title(stationsList.get(i).getName()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 16f));
        }
    }
}
