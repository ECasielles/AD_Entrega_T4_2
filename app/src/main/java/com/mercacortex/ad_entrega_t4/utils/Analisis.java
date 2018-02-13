package com.mercacortex.ad_entrega_t4.utils;

import android.content.res.Resources;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import fr.arnaudguyon.xmltojsonlib.JsonToXml;


public class Analisis {

    public static WeatherGSON analizeWeatherGSON(JSONObject response) throws JSONException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(String.valueOf(response), WeatherGSON.class);
    }

    //Lee el archivo
    public static ArrayList<CityWeather> readSpainCitiesFromAsset() throws JSONException, IOException {
        ArrayList<CityWeather> cities = new ArrayList<>();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream stream = Resources.getSystem().getAssets().open("cities:spain.json");
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
            throw new IOException("Error leyendo asset JSON en cities_spain.json");
        }
        return cities;
    }

    public static void downloadContent(JSONObject response, String cityName) throws FileNotFoundException {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), cityName + ".json");
        PrintWriter out = new PrintWriter(file);
        out.print(response.toString());

        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), cityName + ".xml");
        out = new PrintWriter(file);

        //Usando esta librería: https://github.com/smart-fun/XmlToJson
        JsonToXml jsonToXml = new JsonToXml.Builder(response).build();
        int indentationSize = 3;
        out.print(jsonToXml.toFormattedString(indentationSize));
    }
}
