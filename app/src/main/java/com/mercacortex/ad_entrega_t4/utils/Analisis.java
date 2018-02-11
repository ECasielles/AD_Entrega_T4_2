package com.mercacortex.ad_entrega_t4.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercacortex.ad_entrega_t4.model.WeatherGSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by icenri on 2/11/18.
 */

public class Analisis {

    public static WeatherGSON analizeWeatherGSON(JSONObject respuesta) throws JSONException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(String.valueOf(respuesta), WeatherGSON.class);
    }

}
