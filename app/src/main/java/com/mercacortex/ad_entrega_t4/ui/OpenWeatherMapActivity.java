package com.mercacortex.ad_entrega_t4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.model.CityWeather;

import java.util.ArrayList;

public class OpenWeatherMapActivity extends AppCompatActivity implements IOpenWeatherMap {
    public static final String TAG = "OpenWeatherMapActivity";

    ListView listView;
    ArrayList<CityWeather> cities;
    ArrayAdapter<CityWeather> cityWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_weather_map);

        listView = findViewById(android.R.id.list);
        cities = new ArrayList<>();

        cities.add(new CityWeather(2514256, "Malaga"));
        cities.add(new CityWeather(3441575, "Madrid"));
        cities.add(new CityWeather(3128760, "Barcelona"));
        cities.add(new CityWeather(1814697, "Cuihuangkou"));
        cities.add(new CityWeather(2512177, "Puertollano"));
        cities.add(new CityWeather(3390760, "Recife"));
        cities.add(new CityWeather(6231568, "Takanini"));
        cities.add(new CityWeather(3441575, "Montevideo"));
        cities.add(new CityWeather(81302, "Tobruk"));
        cities.add(new CityWeather(2729907, "Longyearbyen"));
        cities.add(new CityWeather(2204582, "Lambasa"));
        cities.add(new CityWeather(5880054, "Barrow"));
        cities.add(new CityWeather(1264527, "Chennai"));
        cities.add(new CityWeather(1155231, "Ban Phru Nai"));
        cities.add(new CityWeather(1168036, "Pind Dadan Khan"));

        cityWeatherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(OpenWeatherMapActivity.this, CityWeatherActivity.class);
                intent.putExtra(CITY, cities.get(position));
                intent.putExtra(KEY, TAG);
                startActivity(intent);
            }
        });
        listView.setAdapter(cityWeatherAdapter);
    }

}
