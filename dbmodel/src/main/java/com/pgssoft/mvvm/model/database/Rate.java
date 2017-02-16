package com.pgssoft.mvvm.model.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Rate {
    public static final String FIELD_ID = "id";
    public static final String FIELD_TABLE = "table";
    public static final String FIELD_NAME = "currencyName";
    public static final String FIELD_RATE = "rate";

    @DatabaseField(columnName = FIELD_ID, id = true)
    private String currencyCode;

    @DatabaseField(columnName = FIELD_TABLE, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Table table;

    @DatabaseField(columnName = FIELD_NAME, canBeNull = false)
    private String currencyName;

    @DatabaseField(columnName = FIELD_RATE, canBeNull = false)
    private double rate;

    public Rate() {
    }

    public Rate(String currencyCode, Table table, String currencyName, double rate) {
        this.currencyCode = currencyCode;
        this.table = table;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

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
}
