package com.mercacortex.ad_entrega_t4.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.model.AirportGSON;

import java.util.ArrayList;

public class AirportsAdapter extends RecyclerView.Adapter<AirportsAdapter.AirportViewHolder> {

    Context context;
    ArrayList<AirportGSON.Airport> airports = new ArrayList<>();
    OnItemActionListener listener;

    public AirportsAdapter(Context context, OnItemActionListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public AirportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AirportViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_airport, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(AirportViewHolder holder, int position) {
        holder.txvAirport.setText(airports.get(position).toString());
        holder.bind(airports.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void clear() {
        airports.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<AirportGSON.Airport> airportArrayList) {
        if (airportArrayList != null) {
            airports.addAll(airportArrayList);
            notifyDataSetChanged();
        }
    }

    public class AirportViewHolder extends RecyclerView.ViewHolder {
        TextView txvAirport;

        public AirportViewHolder(View itemView) {
            super(itemView);
            txvAirport = itemView.findViewById(R.id.txvAirport);
        }

        public void bind(final AirportGSON.Airport airport, final OnItemActionListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(airport);
                }
            });
        }
    }

}
