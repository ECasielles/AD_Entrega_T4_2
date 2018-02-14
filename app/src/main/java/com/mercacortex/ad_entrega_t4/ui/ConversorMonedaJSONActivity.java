package com.mercacortex.ad_entrega_t4.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mercacortex.ad_entrega_t4.R;
import com.mercacortex.ad_entrega_t4.api.CurrencyAPI;
import com.mercacortex.ad_entrega_t4.network.RestClient;
import com.mercacortex.ad_entrega_t4.utils.Analisis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class ConversorMonedaJSONActivity extends AppCompatActivity {

    HashMap<String, Float> exchangeTable;
    Spinner spnCurr1, spnCurr2;
    EditText edtCurr1, edtCurr2;
    SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor_moneda_json);

        spnCurr1 = findViewById(R.id.spnCurr1);
        spnCurr2 = findViewById(R.id.spnCurr2);
        edtCurr1 = findViewById(R.id.edtCurr1);
        edtCurr2 = findViewById(R.id.edtCurr2);

        loadSpinners();
    }

    void loadSpinners() {
        String url = CurrencyAPI.loadEuroRates();
        RestClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    exchangeTable = Analisis.analizeCurrCodes(response);
                    String[] currCodes = exchangeTable.keySet().toArray(new String[0]);
                    spinnerAdapter = new ArrayAdapter<>(
                            ConversorMonedaJSONActivity.this,
                            android.R.layout.simple_spinner_item,
                            currCodes
                    );
                    spnCurr1.setAdapter(spinnerAdapter);
                    spnCurr2.setAdapter(spinnerAdapter);
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
