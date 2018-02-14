package com.mercacortex.ad_entrega_t4.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mercacortex.ad_entrega_t4.api.CurrencyAPI;
import com.mercacortex.ad_entrega_t4.model.AirportGSON;
import com.mercacortex.ad_entrega_t4.model.CityGSON;
import com.mercacortex.ad_entrega_t4.model.CityWeather;
import com.mercacortex.ad_entrega_t4.model.WeatherGSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import fr.arnaudguyon.xmltojsonlib.JsonToXml;


public class Analisis {

    public static WeatherGSON analizeWeatherGSON(JSONObject response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(String.valueOf(response), WeatherGSON.class);
    }

    public static CityGSON analizeForecastGSON(JSONObject response) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(String.valueOf(response), CityGSON.class);
    }

    //Lee el archivo de ciudades de España
    public static ArrayList<CityWeather> readSpainCitiesFromAsset(Context context) throws JSONException, IOException {
        ArrayList<CityWeather> cities = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream stream = context.getResources().getAssets().open("my_cities.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("cities");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonCity = jsonArray.getJSONObject(i);
                CityWeather cityWeather = new CityWeather();
                cityWeather.setId(jsonCity.getInt("id"));
                cityWeather.setName(jsonCity.getString("city"));
                cities.add(cityWeather);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error leyendo assets");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException("Error leyendo asset JSON en my_cities.json");
        }
        return cities;
    }

    //Guarda en archivo
    public static void downloadForecastContent(JSONObject response, String cityName) throws FileNotFoundException, JSONException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), cityName + ".json");
        PrintWriter out = new PrintWriter(file);
        out.print(response.toString());

        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), cityName + ".xml");
        out = new PrintWriter(file);

        JSONObject rootObject = new JSONObject();
        rootObject.put(cityName.toLowerCase().replace(" ", "_"), response);

        //Usando esta librería: https://github.com/smart-fun/XmlToJson
        JsonToXml.Builder builder = new JsonToXml.Builder(rootObject);
        JsonToXml jsonToXml = builder.build();
        int indentationSize = 3;
        String cityXml = jsonToXml.toFormattedString(indentationSize);
        Log.d("Analisis", "downloadForecastContent: " + jsonToXml.toString());
        out.print(cityXml);
        out.close();
    }

    //Genera la tabla de conversión.
    //Es necesario usar LinkedHashMap para preservar el orden al añadir y que
    //se mantenga el orden alfabético (salvo EUR, que se pone primero por conveniencia y
    //porque no sale en la lista al ser la base de cambio)
    public static LinkedHashMap<String, Float> analizeCurrCodes(JSONObject response) throws JSONException, NumberFormatException {
        LinkedHashMap<String, Float> exchangeTable = new LinkedHashMap<>();
        JSONObject jsonObject = response.getJSONObject("rates");
        Iterator keys = jsonObject.keys();
        exchangeTable.put(CurrencyAPI.EUR, 1f);
        while (keys.hasNext()) {
            String code = (String) keys.next();
            exchangeTable.put(code, Float.parseFloat(String.valueOf(jsonObject.get(code))));
        }
        return exchangeTable;
    }

    public static ArrayList<AirportGSON.Airport> analizeAirportGSON(JSONArray response) throws JSONException {
        Gson gson = new GsonBuilder().create();
        ArrayList<AirportGSON.Airport> airports = new ArrayList<>();
        for (int i = 0; i < response.length(); i++) {
            airports.add(gson.fromJson(String.valueOf(response.getJSONObject(i)), AirportGSON.Airport.class));
        }
        return airports;
    }

}
