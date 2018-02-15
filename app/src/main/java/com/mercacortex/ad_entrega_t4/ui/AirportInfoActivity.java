package com.mercacortex.ad_entrega_t4.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.mercacortex.ad_entrega_t4.R;

public class AirportInfoActivity extends AppCompatActivity {

    TextView txvHtmlInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_info);
        txvHtmlInfo = findViewById(R.id.txvHtmlInfo);
        txvHtmlInfo.setText(Html.fromHtml(
                getIntent().getStringExtra(DatosAbiertosActivity.CONTENT)
        ));
    }

}
