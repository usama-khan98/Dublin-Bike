package com.example.dublinbikestations;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//Usama Ali Khan (17810)
// Isaac Costa Da Silva(18735)

//adapter class for recycling views in recyclerViewWidget
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemsViewHolder>{


    private Context mContext;
    private List<StationData> mData;

    public RecyclerAdapter(Context mContext, List<?> Data) {
        this.mContext = mContext;
        this.mData = (List<StationData>) Data;

    }

    @NonNull
    @Override
    //overriding the onCreateViewHolder to inflate the recycler_layout in the recyclerView

    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView= LayoutInflater.from(mContext).inflate(R.layout.recycler_layout,
                parent,false);

        return new ItemsViewHolder(mView);
    }


    @Override
    //binding the data from list to the recycler view items

    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {

        final StationData gItem=mData.get(position);


        holder.stationName.setText(gItem.getName());
        holder.stationItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(mContext,StationInfoActivity.class);
                myIntent.putExtra("item",gItem);
                mContext.startActivity(myIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    //binding the itemViews in recyclerView to their instances
    public static class ItemsViewHolder extends RecyclerView.ViewHolder{

        TextView stationName;
        RelativeLayout stationItem;


        public ItemsViewHolder(View itemView) {
            super(itemView);

            stationName=itemView.findViewById(R.id.station_name);
            stationItem=itemView.findViewById(R.id.station_item);

        }
    }

    public void updateList(List<StationData> newList){
        mData=new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }

}
