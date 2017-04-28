package com.pgssoft.mvvm.services.mock;

import com.pgssoft.mvvm.constant.DateFormats;
import com.pgssoft.mvvm.model.api.ApiRate;
import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;
import com.pgssoft.mvvm.models.dto.RateDto;
import com.pgssoft.mvvm.services.interfaces.MapperService;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public class ObjectMapper implements MapperService {
    @Override
    public Rate map(ApiRate apiApiRate) {
        return new Rate(apiApiRate.getCurrencyCode(), null, apiApiRate.getCurrencyName(), apiApiRate.getRate());
    }

    @Override
    public Table map(ApiTable apiApiTable) {
        return new Table(apiApiTable.getId(), apiApiTable.getNumber(),
                DateTime.parse(apiApiTable.getLastUpdate(),
                DateTimeFormat.forPattern(DateFormats.LAST_UPDATE_DATE_FORMAT)).toDate());
    }

    @Override
    public RateDto map(Rate rate) {
        return new RateDto(rate.getCurrencyCode(), rate.getCurrencyName(), rate.getRate());
    }
}
