package com.mercacortex.ad_entrega_t4.ui.openweathermap.weather;

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
import com.mercacortex.ad_entrega_t4.ui.openweathermap.IOpenWeatherMap;
import com.mercacortex.ad_entrega_t4.utils.Analisis;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class CityWeatherActivity extends AppCompatActivity {
    private TextView txvName, txvTemperature, txvPressure, txvHumidity;
    private ImageView imvForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather);

        txvName = findViewById(R.id.txvDate);
        txvTemperature = findViewById(R.id.txvTemperature);
        txvPressure = findViewById(R.id.txvPressure);
        txvHumidity = findViewById(R.id.txvHumidity);
        imvForecast = findViewById(R.id.imvForecast);

        CityWeather cityWeather = getIntent().getParcelableExtra(IOpenWeatherMap.CITY);
        if (cityWeather != null) {
            String url = WeatherAPI.loadCityWeather(cityWeather.getId());
            if (!url.isEmpty())
                download(url);
            else
                showMessage("Fallo en la conexión");
        }
    }

    private void download(String url) {
        RestClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                    WeatherGSON weatherGSON = Analisis.analizeWeatherGSON(response);
                    txvName.setText(String.format(
                            "El tiempo en: %s",
                            weatherGSON.name)
                    );
                    txvTemperature.setText(String.format(
                            "Temperatura: %sºC",
                            weatherGSON.main.getTemp())
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
