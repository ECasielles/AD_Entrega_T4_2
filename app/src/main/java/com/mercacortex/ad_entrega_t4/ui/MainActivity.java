package com.mercacortex.ad_entrega_t4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mercacortex.ad_entrega_t4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOpenWeatherMap:
                startActivity(new Intent(this, OpenWeatherMapActivity.class));
                break;
            case R.id.btnOpenWeatherMapEspaña:
                startActivity(new Intent(this, OpenWeatherMapSpainActivity.class));
                break;
            case R.id.btnConversorMonedaJSON:
                startActivity(new Intent(this, ConversorMonedaJSONActivity.class));
                break;
            case R.id.btnBiziGSON:
                startActivity(new Intent(this, BiziGSONActivity.class));
                break;
            case R.id.btnDatosAbiertos:
                startActivity(new Intent(this, DatosAbiertosActivity.class));
                break;

        }
    }
}
