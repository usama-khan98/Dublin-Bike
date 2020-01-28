package com.example.dublinbikestations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class StationsListActivity extends AppCompatActivity {

    RecyclerView stationRecycler;
    RecyclerAdapter recycleStationAdapter;

//Usama Ali Khan (17810)
// Isaac Costa Da Silva(18735)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_list);

        List<StationData> list = MainActivity.stationsList;
        //to get the backButton on actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        stationRecycler=findViewById(R.id.recycleview_stations);

        //recycler adapter creation with stationsList

        recycleStationAdapter=new RecyclerAdapter(this, list);

        stationRecycler.setLayoutManager(new LinearLayoutManager(this));
        stationRecycler.setItemAnimator(new DefaultItemAnimator());
        stationRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        stationRecycler.setAdapter(recycleStationAdapter);
        //to update data displayed in recycler view

        recycleStationAdapter.notifyDataSetChanged();





    }
    @Override
    public boolean onSupportNavigateUp() {
        //to create button in actionBar for navigation to MainActivity/PreviousScreen
        this.startActivity(new Intent(this,MainActivity.class));
        return true;
    }
}
