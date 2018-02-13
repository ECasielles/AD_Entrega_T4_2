package com.mercacortex.ad_entrega_t4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityGSON {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private int population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public class Coord {

        @SerializedName("lon")
        @Expose
        private float lon;
        @SerializedName("lat")
        @Expose
        private float lat;

        public float getLon() {
            return lon;
        }

        public void setLon(float lon) {
            this.lon = lon;
        }

        public float getLat() {
            return lat;
        }

        public void setLat(float lat) {
            this.lat = lat;
        }

    }

    public class List {

        @SerializedName("dt")
        @Expose
        private int dt;
        @SerializedName("temp")
        @Expose
        private Temp temp;
        @SerializedName("pressure")
        @Expose
        private float pressure;
        @SerializedName("humidity")
        @Expose
        private int humidity;
        @SerializedName("weather")
        @Expose
        private java.util.List<Weather> weather = null;
        @SerializedName("speed")
        @Expose
        private float speed;
        @SerializedName("deg")
        @Expose
        private int deg;
        @SerializedName("clouds")
        @Expose
        private int clouds;
        @SerializedName("rain")
        @Expose
        private float rain;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public java.util.List<Weather> getWeather() {
            return weather;
        }

        public void setWeather(java.util.List<Weather> weather) {
            this.weather = weather;
        }

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public int getClouds() {
            return clouds;
        }

        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        public float getRain() {
            return rain;
        }

        public void setRain(float rain) {
            this.rain = rain;
        }

    }

    public class Temp {

        @SerializedName("day")
        @Expose
        private float day;
        @SerializedName("min")
        @Expose
        private float min;
        @SerializedName("max")
        @Expose
        private float max;
        @SerializedName("night")
        @Expose
        private float night;
        @SerializedName("eve")
        @Expose
        private float eve;
        @SerializedName("morn")
        @Expose
        private float morn;

        public float getDay() {
            return day;
        }

        public void setDay(float day) {
            this.day = day;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getNight() {
            return night;
        }

        public void setNight(float night) {
            this.night = night;
        }

        public float getEve() {
            return eve;
        }

        public void setEve(float eve) {
            this.eve = eve;
        }

        public float getMorn() {
            return morn;
        }

        public void setMorn(float morn) {
            this.morn = morn;
        }

    }

    public class Weather {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("main")
        @Expose
        private String main;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("icon")
        @Expose
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
}
