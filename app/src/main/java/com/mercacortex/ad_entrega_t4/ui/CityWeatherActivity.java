package com.mercacortex.ad_entrega_t4.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.api.WeatherAPI;
import com.mercacortex.ad_entrega_t4.model.CityWeather;
import com.mercacortex.ad_entrega_t4.model.WeatherGSON;
import com.mercacortex.ad_entrega_t4.network.RestClient;
import com.mercacortex.ad_entrega_t4.utils.Analisis;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class CityWeatherActivity extends AppCompatActivity {

    private static final int DAYS = 7;
    private TextView txvName, txvTemperature, txvMaxMinTemperature,
            txvPressure, txvHumidity;
    private ImageView imvForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        txvName = findViewById(R.id.txvName);
        txvTemperature = findViewById(R.id.txvTemperature);
        txvMaxMinTemperature = findViewById(R.id.txvMaxMinTemperature);
        txvPressure = findViewById(R.id.txvPressure);
        txvHumidity = findViewById(R.id.txvHumidity);
        imvForecast = findViewById(R.id.imvForecast);

        CityWeather cityWeather = getIntent().getParcelableExtra(IOpenWeatherMap.CITY);
        String url = "";
        String tag = getIntent().getStringExtra(IOpenWeatherMap.KEY);
        switch (tag) {
            case OpenWeatherMapActivity.TAG:
                url = WeatherAPI.loadCityWeather(cityWeather.getId());
                break;
            case OpenWeatherMapSpainActivity.TAG:
                url = WeatherAPI.loadCityDailyForecast(cityWeather.getId(), DAYS);
                break;
        }
        if (!url.isEmpty())
            descarga(url, tag, cityWeather.getName());
    }

    private void descarga(String url, final String tag, final String cityName) {
        RestClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    WeatherGSON weatherGSON = Analisis.analizeWeatherGSON(response);
                    txvName.setText(String.format(
                            "El tiempo en: %s",
                            weatherGSON.name)
                    );
                    txvTemperature.setText(String.format(
                            "Temperatura: %sºC",
                            weatherGSON.main.getTemp())
                    );
                    txvMaxMinTemperature.setText(String.format(
                            "Temperatura M/m: %s/%sºC",
                            weatherGSON.main.getTempMax(), weatherGSON.main.getTempMin())
                    );
                    txvPressure.setText(String.format(Locale.getDefault(),
                            "Presión: %dhpa",
                            weatherGSON.main.getPressure())
                    );
                    txvHumidity.setText(String.format(
                            "Humedad: %s%%",
                            weatherGSON.main.getHumidity())
                    );
                    Picasso.with(CityWeatherActivity.this)
                            .load(WeatherAPI.loadWeatherIcon(weatherGSON.weather.get(0).icon))
                            .into(imvForecast);
                    if (tag.equals(OpenWeatherMapSpainActivity.TAG))
                        try {
                            Analisis.downloadContent(response, cityName);
                        } catch (FileNotFoundException e) {
                            showMessage("Error de escritura en archivo: " + e.getLocalizedMessage());
                        }
                } catch (JSONException e) {
                    showMessage("Error de lectura de JSON: " + e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                showMessage(throwable.getLocalizedMessage());
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
