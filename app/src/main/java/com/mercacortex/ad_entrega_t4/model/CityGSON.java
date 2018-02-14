package com.mercacortex.ad_entrega_t4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CityGSON {

    @SerializedName("city")
    @Expose
    public City city;
    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("message")
    @Expose
    public Float message;
    @SerializedName("cnt")
    @Expose
    public Integer cnt;
    @SerializedName("list")
    @Expose
    public java.util.List<CityGSON.List> list = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Float getMessage() {
        return message;
    }

    public void setMessage(Float message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }


    public class City {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("coord")
        @Expose
        public Coord coord;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("population")
        @Expose
        public Integer population;

    }

    public class Coord {

        @SerializedName("lon")
        @Expose
        public Float lon;
        @SerializedName("lat")
        @Expose
        public Float lat;

    }

    public class List {

        @SerializedName("dt")
        @Expose
        public Long dt;
        @SerializedName("temp")
        @Expose
        public Temp temp;
        @SerializedName("pressure")
        @Expose
        public Float pressure;
        @SerializedName("humidity")
        @Expose
        public Integer humidity;
        @SerializedName("weather")
        @Expose
        public java.util.List<Weather> weather = null;
        @SerializedName("speed")
        @Expose
        public Float speed;
        @SerializedName("deg")
        @Expose
        public Integer deg;
        @SerializedName("clouds")
        @Expose
        public Integer clouds;
        @SerializedName("rain")
        @Expose
        public Float rain;

    }

    public class Temp {

        @SerializedName("day")
        @Expose
        public Float day;
        @SerializedName("min")
        @Expose
        public Float min;
        @SerializedName("max")
        @Expose
        public Float max;
        @SerializedName("night")
        @Expose
        public Float night;
        @SerializedName("eve")
        @Expose
        public Float eve;
        @SerializedName("morn")
        @Expose
        public Float morn;

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
}


