package com.mercacortex.ad_entrega_t4.ui.openweathermap.forecast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.model.CityWeather;
import com.mercacortex.ad_entrega_t4.ui.openweathermap.IOpenWeatherMap;
import com.mercacortex.ad_entrega_t4.utils.Analisis;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class OpenWeatherMapSpainActivity extends AppCompatActivity implements IOpenWeatherMap {
    private static ArrayList<CityWeather> cities;
    private ListView listView;
    private ArrayAdapter<CityWeather> cityWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_weather_map_spain);

        listView = findViewById(android.R.id.list);
        loadCities();

        cityWeatherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(OpenWeatherMapSpainActivity.this, CityForecastActivity.class);
                intent.putExtra(CITY, cities.get(position));
                startActivity(intent);
            }
        });
        listView.setAdapter(cityWeatherAdapter);
    }

    private void loadCities() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        cities = new ArrayList<>();
        try {
            cities = Analisis.readSpainCitiesFromAsset(this);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();
    }

}
