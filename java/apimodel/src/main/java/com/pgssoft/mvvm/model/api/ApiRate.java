package com.pgssoft.mvvm.model.api;

import com.google.gson.annotations.SerializedName;

public class ApiRate {
    @SerializedName("code")
    private String currencyCode;

    @SerializedName("currency")
    private String currencyName;

    @SerializedName("mid")
    private double rate;

    public ApiRate(String currencyCode, String currencyName, double rate) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getRate() {
        return rate;
    }
}
