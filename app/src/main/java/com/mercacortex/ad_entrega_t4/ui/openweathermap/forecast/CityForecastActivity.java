package com.mercacortex.ad_entrega_t4.ui.openweathermap.forecast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.adapter.CityForecastAdapter;
import com.mercacortex.ad_entrega_t4.api.WeatherAPI;
import com.mercacortex.ad_entrega_t4.model.CityGSON;
import com.mercacortex.ad_entrega_t4.model.CityWeather;
import com.mercacortex.ad_entrega_t4.network.RestClient;
import com.mercacortex.ad_entrega_t4.ui.openweathermap.IOpenWeatherMap;
import com.mercacortex.ad_entrega_t4.utils.Analisis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

public class CityForecastActivity extends AppCompatActivity {
    private static final int DAYS = 7;
    ListView listView;
    CityForecastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_forecast);

        listView = findViewById(android.R.id.list);
        adapter = new CityForecastAdapter(this);
        listView.setAdapter(adapter);

        CityWeather cityWeather = getIntent().getParcelableExtra(IOpenWeatherMap.CITY);
        if (cityWeather != null) {
            String url = WeatherAPI.loadCityDailyForecast(cityWeather.getId(), DAYS);
            if (!url.isEmpty())
                download(url, cityWeather.getName());
            else
                showMessage("Fallo en la conexión");
        }

    }

    private void download(String url, final String cityName) {

        RestClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                loadContent(response, cityName);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                showMessage(throwable.getLocalizedMessage());
            }
        });
    }

    private void loadContent(JSONObject response, String cityName) {
        try {
            Analisis.downloadForecastContent(response, cityName);
            CityGSON cityData = Analisis.analizeForecastGSON(response);
            adapter.clear();
            adapter.addAll(cityData.getList());
        } catch (FileNotFoundException e) {
            showMessage("Error de escritura en archivo: " + e.getLocalizedMessage());
        } catch (JSONException e) {
            showMessage("Error de lectura de JSON: " + e.getLocalizedMessage());
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
