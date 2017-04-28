package com.pgssoft.kotlinplayground.service.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.model.db.Table

import java.sql.SQLException

/**
 * Created by bstokrocki on 16.02.2017.
 */

class DBHelper(context: Context) : OrmLiteSqliteOpenHelper(context, DBHelper.DATABASE_NAME, null, DBHelper.DATABASE_VERSION) {
    private companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "mvvm.db"
    }

    val tableDao: Dao<Table, Int> by lazy {
        try {
            getDao<Dao<Table, Int>, Table>(Table::class.java)
        } catch (ex: SQLException) {
            throw RuntimeException(ex)
        }
    }

    val rateDao: Dao<Rate, Int> by lazy {
        try {
            getDao<Dao<Rate, Int>, Rate>(Rate::class.java)
        } catch (ex: SQLException) {
            throw RuntimeException(ex)
        }
    }

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            TableUtils.createTable<Table>(connectionSource, Table::class.java)
            TableUtils.createTable<Rate>(connectionSource, Rate::class.java)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        try {
            TableUtils.dropTable<Table, Any>(connectionSource, Table::class.java, true)
            TableUtils.dropTable<Rate, Any>(connectionSource, Rate::class.java, true)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }

        onCreate(database, connectionSource)
    }
}
