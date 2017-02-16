package com.pgssoft.mvvm.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public class ApiTable {
    @SerializedName("table")
    private String id;

    @SerializedName("no")
    private String number;

    @SerializedName("effectiveDate")
    private String lastUpdate;

    @SerializedName("rates")
    private List<ApiRate> apiRates;

    public ApiTable(String name, String number, String lastUpdate, List<ApiRate> apiRates) {
        this.id = name;
        this.number = number;
        this.lastUpdate = lastUpdate;
        this.apiRates = apiRates;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public List<ApiRate> getApiRates() {
        return apiRates;
    }
}
