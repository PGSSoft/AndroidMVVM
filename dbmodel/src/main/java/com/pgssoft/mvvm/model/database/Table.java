package com.pgssoft.mvvm.model.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bstokrocki on 30.01.2017.
 */
@DatabaseTable
public class Table {
    public static final String FIELD_ID = "id";
    public static final String FIELD_NUMBER = "number";
    public static final String FIELD_LAST_UPDATE = "lastUpdate";

    @DatabaseField(columnName = FIELD_ID, id = true)
    private String id;

    @DatabaseField(columnName = FIELD_NUMBER, canBeNull = false)
    private String number;

    @DatabaseField(columnName = FIELD_LAST_UPDATE, canBeNull = false, dataType = DataType.DATE_STRING)
    private Date lastUpdate;

    public Table() {
    }

    public Table(String id, String number, Date lastUpdate) {
        this.id = id;
        this.number = number;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
