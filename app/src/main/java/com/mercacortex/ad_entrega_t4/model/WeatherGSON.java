package com.mercacortex.ad_entrega_t4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherGSON {

    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    public String base;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("visibility")
    @Expose
    public Integer visibility;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("cod")
    @Expose
    public Integer cod;

    public class Clouds {

        @SerializedName("all")
        @Expose
        public Integer all;

    }

    public class Coord {

        @SerializedName("lon")
        @Expose
        public Float lon;
        @SerializedName("lat")
        @Expose
        public Float lat;

    }

    public class Main {

        @SerializedName("temp")
        @Expose
        public Float temp;
        @SerializedName("pressure")
        @Expose
        public Integer pressure;
        @SerializedName("humidity")
        @Expose
        public Integer humidity;
        @SerializedName("temp_min")
        @Expose
        public Float tempMin;
        @SerializedName("temp_max")
        @Expose
        public Float tempMax;

        public Float getTemp() {
            return temp - 273.15f;
        }

        public Integer getPressure() {
            return pressure;
        }

        public Integer getHumidity() {
            return humidity;
        }

        public Float getTempMax() {
            return tempMax;
        }

        public Float getTempMin() {
            return tempMin;
        }
    }

    public class Sys {

        @SerializedName("type")
        @Expose
        public Integer type;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("message")
        @Expose
        public Float message;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("sunrise")
        @Expose
        public Integer sunrise;
        @SerializedName("sunset")
        @Expose
        public Integer sunset;

    }

    public class Weather {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("main")
        @Expose
        public String main;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("icon")
        @Expose
        public String icon;

    }

    public class Wind {

        @SerializedName("speed")
        @Expose
        public Float speed;
        @SerializedName("deg")
        @Expose
        public Integer deg;

    }

}



