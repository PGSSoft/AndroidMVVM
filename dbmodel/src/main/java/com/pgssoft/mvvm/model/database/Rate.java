package com.pgssoft.mvvm.model.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Rate {
    @DatabaseField(id = true)
    private String currencyCode;

    @DatabaseField(canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Table table;

    @DatabaseField(canBeNull = false)
    private String currencyName;

    @DatabaseField(canBeNull = false)
    private double rate;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Double getRate() {
        return rate;
    }

    public Table getTable() {
        return table;
    }

    public Rate(String currencyCode, Table table, String currencyName, double rate) {
        this.currencyCode = currencyCode;
        this.table = table;
        this.currencyName = currencyName;
        this.rate = rate;
    }
}
