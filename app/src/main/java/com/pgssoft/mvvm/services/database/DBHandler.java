package com.pgssoft.mvvm.services.database;

import android.content.Context;

import com.pgssoft.mvvm.model.api.ApiRate;
import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;
import com.pgssoft.mvvm.models.dto.RateDto;
import com.pgssoft.mvvm.services.interfaces.MapperService;
import com.pgssoft.mvvm.services.interfaces.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by bstokrocki on 16.02.2017.
 */

public class DBHandler implements Repository {
    private final DBHelper dbHelper;
    private final MapperService mapperService;

    public DBHandler(Context applicationContext, MapperService mapperService) {
        this.dbHelper = new DBHelper(applicationContext);
        this.mapperService = mapperService;
    }

    @Override
    public void saveTables(ApiTable[] apiTables) {
        for (ApiTable apiTable : apiTables) {
            try {
                Table table = mapperService.map(apiTable);
                dbHelper.getTableDao().createOrUpdate(table);

                for (ApiRate apiRate : apiTable.getApiRates()) {
                    Rate rate = mapperService.map(apiRate);
                    rate.setTable(table);
                    dbHelper.getRateDao().createOrUpdate(rate);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Rate> getAllRates() {
        try {
            return dbHelper.getRateDao().queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RateDto findRateById(String rateId) {
        try {
            Rate rate = dbHelper.getRateDao().queryBuilder().where()
                    .eq(Rate.FIELD_ID, rateId)
                    .queryForFirst();

            return rate != null ? mapperService.map(rate) : null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
