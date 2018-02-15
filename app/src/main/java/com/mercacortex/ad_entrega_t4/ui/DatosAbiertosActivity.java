package com.mercacortex.ad_entrega_t4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.adapter.AirportsAdapter;
import com.mercacortex.ad_entrega_t4.adapter.OnItemActionListener;
import com.mercacortex.ad_entrega_t4.api.OpenDataAPI;
import com.mercacortex.ad_entrega_t4.model.AirportGSON;
import com.mercacortex.ad_entrega_t4.network.RestClient;
import com.mercacortex.ad_entrega_t4.utils.Analisis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class DatosAbiertosActivity extends AppCompatActivity implements OnItemActionListener {

    public static final String CONTENT = "content";
    private ArrayList<AirportGSON.Airport> airports;
    private RecyclerView recycler;
    private AirportsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_abiertos);
        airports = new ArrayList<>();
        adapter = new AirportsAdapter(this, this);
        recycler = findViewById(android.R.id.list);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void onClick(View view) {
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
                showMessage("Error en la descarga: " + throwable.getLocalizedMessage());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                showMessage("Error en la descarga: " + throwable.getLocalizedMessage());
            }
        });
    }

    void loadContent(JSONArray response) {
        try {
            airports = Analisis.analizeAirportGSON(response);
            adapter.clear();
            adapter.addAll(airports);
            showMessage("Carga con éxito");
            recycler.setAdapter(adapter);
            showMessage(adapter.getItemCount() + "");
            showMessage(recycler.getAdapter().getItemCount() + "");
        } catch (JSONException e) {
            showMessage("Error de lectura de JSON: " + e.getLocalizedMessage());
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemClick(AirportGSON.Airport airport) {
        Intent intent = new Intent(DatosAbiertosActivity.this, AirportInfoActivity.class);
        intent.putExtra(CONTENT, airport.getDescription());
        startActivity(intent);
    }
}
