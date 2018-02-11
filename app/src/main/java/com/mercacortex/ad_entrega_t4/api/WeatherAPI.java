package com.mercacortex.ad_entrega_t4.api;

public class WeatherAPI {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String ID = "?id=";
    private static final String API_ID = "&appid=6d4b06312a47c5d3a6c7d4a550306e02";

    private static final String ICON_URL = "http://openweathermap.org/img/w/";
    private static final String ICON_IMAGE_FORMAT = ".png";

    public static String loadCityInfoFromId(int cityId) {
        return BASE_URL + ID + cityId + API_ID;
    }

    public static String loadWeatherIcon(String iconName) {
        return ICON_URL + iconName + ICON_IMAGE_FORMAT;
    }

}
