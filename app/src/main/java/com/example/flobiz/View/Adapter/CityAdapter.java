package com.example.flobiz.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flobiz.Model.City;
import com.example.flobiz.R;
import com.example.flobiz.View.UI.CityDetailsActivity;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>{
    private List<City> cities;
    private int rowLayout;
    private Context context;
    public CityAdapter(List<City> cities, int rowLayout, Context context) {
        this.cities = cities;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID
    public static class CityViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;
        public CityViewHolder(View v) {
            super(v);
            cityName = (TextView) v.findViewById(R.id.cityName);
        }
    }
    @Override
    public CityAdapter.CityViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CityViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CityViewHolder holder, final int position) {
        final Context mContext = context;
        holder.cityName.setText(cities.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //use position value  to get clicked data from list
                Log.d("Clicked",cities.get(position).getName());
                Intent i= new Intent(context, CityDetailsActivity.class);
                i.putExtra("city",cities.get(position).getName());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return cities.size();
    }
}