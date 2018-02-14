package com.mercacortex.ad_entrega_t4.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.api.OpenDataAPI;
import com.mercacortex.ad_entrega_t4.model.AirportGSON;
import com.mercacortex.ad_entrega_t4.network.RestClient;
import com.mercacortex.ad_entrega_t4.utils.Analisis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class DatosAbiertosActivity extends AppCompatActivity {

    private ArrayList<AirportGSON.Airport> airports;
    private ListView listView;
    private ArrayAdapter<AirportGSON.Airport> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_abiertos);
        airports = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, airports);
        listView = findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        download(OpenDataAPI.BASE_URL);
    }

    void download(String url) {
        RestClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                loadContent(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                showMessage(throwable.getLocalizedMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                showMessage(throwable.getLocalizedMessage());
            }
        });
    }

    void loadContent(JSONArray response) {
        try {
            airports = Analisis.analizeAirportGSON(response);
            adapter.clear();
            adapter.addAll(airports);
        } catch (JSONException e) {
            showMessage("Error de lectura de JSON: " + e.getLocalizedMessage());
        }
    }


    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
