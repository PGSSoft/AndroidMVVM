package com.pgssoft.mvvm.services.interfaces;


import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;
import com.pgssoft.mvvm.models.dto.RateDto;

import java.util.List;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public interface Repository {
    void saveTables(ApiTable[] apiTables);

    List<Rate> getAllRates();

    RateDto findRateById(String rateId);
}
