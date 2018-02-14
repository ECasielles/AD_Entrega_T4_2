package com.mercacortex.ad_entrega_t4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.api.WeatherAPI;
import com.mercacortex.ad_entrega_t4.model.CityGSON;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.Locale;


public class CityForecastAdapter extends ArrayAdapter<CityGSON.List> {
    public static final float KELVIN = 273.15f;

    public CityForecastAdapter(@NonNull Context context) {
        super(context, R.layout.activity_city_forecast, new ArrayList<CityGSON.List>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        CityHolder holder;

        if (convertView == null) {
            rootView = LayoutInflater.from(getContext()).
                    inflate(R.layout.item_city_forecast, parent, false);

            holder = new CityHolder();
            holder.imvForecast = rootView.findViewById(R.id.imvForecast);
            holder.txvDate = rootView.findViewById(R.id.txvDate);
            holder.txvMaxMinTemperature = rootView.findViewById(R.id.txvMaxMinTemperature);
            holder.txvPressure = rootView.findViewById(R.id.txvPressure);
            holder.txvHumidity = rootView.findViewById(R.id.txvHumidity);

            rootView.setTag(holder);
        } else
            holder = (CityHolder) rootView.getTag();

        Picasso.with(getContext())
                .load(WeatherAPI.loadWeatherIcon(
                        getItem(position).weather.get(0).icon))
                .into(holder.imvForecast);

        //Fechas usando esta librería: https://github.com/JodaOrg/joda-time
        long dateInSeconds = getItem(position).dt;
        DateTime dt = new DateTime(dateInSeconds * 1000);
        String dateAsString = dt.toString(DateTimeFormat.forPattern("yyyy/MM/dd"));

        holder.txvDate.setText(String.format(
                "El tiempo el día: %s",
                dateAsString
        ));
        holder.txvMaxMinTemperature.setText(String.format(
                "Temperaturas: %.1f/%.1fºC",
                getItem(position).temp.max - KELVIN,
                getItem(position).temp.min - KELVIN
        ));
        holder.txvPressure.setText(String.format(Locale.getDefault(),
                "Presión: %.1fhpa",
                getItem(position).pressure
        ));
        holder.txvHumidity.setText(String.format(
                "Humedad: %s%%",
                getItem(position).humidity
        ));

        return rootView;
    }

    class CityHolder {
        private TextView txvDate, txvMaxMinTemperature, txvPressure, txvHumidity;
        private ImageView imvForecast;
    }

}
