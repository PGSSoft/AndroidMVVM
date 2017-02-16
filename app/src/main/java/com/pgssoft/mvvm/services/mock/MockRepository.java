package com.pgssoft.mvvm.services.mock;

import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;
import com.pgssoft.mvvm.services.interfaces.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public class MockRepository implements Repository {
    @Override
    public void saveTables(ApiTable[] apiTables) {

    }

    @Override
    public List<Rate> getAllRates() {
        ArrayList<Rate> rates = new ArrayList<>();

        rates.add(new Rate("USD", null, "dolar amerykański", 4.04));
        rates.add(new Rate("EUR", null, "euro", 4.32));
        rates.add(new Rate("CHF", null, "frank szwajcarski", 4.05));
        rates.add(new Rate("GBP", null, "funt szterling", 4.05));
        rates.add(new Rate("UAH", null, "hrywna", 0.15));
        rates.add(new Rate("CZK", null, "korona czeska", 0.16));

        return rates;
    }

    @Override
    public Rate findRateById(String rateId) {
        return new Rate("USD", null, "dolar amerykański", 4.04);
    }
}
