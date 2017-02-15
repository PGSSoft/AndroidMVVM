package com.pgssoft.mvvm.model.database;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by bstokrocki on 30.01.2017.
 */
@DatabaseTable
public class Table {
    @DatabaseField(id = true)
    private String id;

    @DatabaseField(canBeNull = false)
    private String number;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private Date lastUpdated;

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Table(String id, String number, Date lastUpdated) {
        this.id = id;
        this.number = number;
        this.lastUpdated = lastUpdated;
    }
}
