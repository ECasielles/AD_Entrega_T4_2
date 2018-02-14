package com.mercacortex.ad_entrega_t4.api;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CurrencyAPI {

    public static final String EUR = "EUR";
    public static final String USD = "USD";
    //https://api.fixer.io/latest
    //https://api.fixer.io/latest?base=USD
    //https://api.fixer.io/latest?symbols=USD,GBP
    private static final String BASE_URL = "https://api.fixer.io/latest";
    private static final String BASE = "?base=";
    private static final String SYMBOLS = "?symbols=%s,%s";

    public static String loadEuroRates() {
        return BASE_URL;
    }

    public static String loadCurrencyRates(@CurrencyName String baseCurrency) {
        return BASE_URL + BASE + baseCurrency;
    }

    public static String loadExchange(@CurrencyName String currency1, @CurrencyName String currency2) {
        return BASE_URL + SYMBOLS + String.format(SYMBOLS, currency1, currency2);
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({EUR, USD})
    public @interface CurrencyName {
    }

}
