package com.mercacortex.ad_entrega_t4.api;

public class WeatherAPI {
    //http://api.openweathermap.org/data/2.5/weather?id=2514256&appid=6d4b06312a47c5d3a6c7d4a550306e02
    //http://api.openweathermap.org/data/2.5/forecast/daily?id=2514256&cnt=7&appid=6d4b06312a47c5d3a6c7d4a550306e02
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String MODE_WEATHER = "weather";
    private static final String MODE_DAILY = "forecast/daily";

    private static final String ID = "?id=";
    private static final String COUNT = "&cnt=";
    private static final String API_ID = "&appid=6d4b06312a47c5d3a6c7d4a550306e02";

    private static final String ICON_URL = "http://openweathermap.org/img/w/";
    private static final String ICON_IMAGE_FORMAT = ".png";

    public static String loadCityWeather(int cityId) {
        return BASE_URL + MODE_WEATHER + ID + cityId + API_ID;
    }

    public static String loadWeatherIcon(String iconName) {
        return ICON_URL + iconName + ICON_IMAGE_FORMAT;
    }

    public static String loadCityDailyForecast(int cityId, int days) {
        return BASE_URL + MODE_DAILY + ID + cityId + COUNT + days + API_ID;
    }

}
