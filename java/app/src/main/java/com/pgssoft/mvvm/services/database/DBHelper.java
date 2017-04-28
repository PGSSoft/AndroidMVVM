package com.pgssoft.mvvm.services.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;

import java.sql.SQLException;

/**
 * Created by bstokrocki on 16.02.2017.
 */

class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mvvm.db";

    private Dao<Table, Integer> tableDao;
    private Dao<Rate, Integer> rateDao;

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Table.class);
            TableUtils.createTable(connectionSource, Rate.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Table.class, true);
            TableUtils.dropTable(connectionSource, Rate.class, true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        onCreate(database, connectionSource);
    }

    Dao<Table, Integer> getTableDao() {
        try {
            if (tableDao == null) {
                tableDao = getDao(Table.class);
            }
            return tableDao;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Dao<Rate, Integer> getRateDao() {
        try {
            if (rateDao == null) {
                rateDao = getDao(Rate.class);
            }
            return rateDao;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
