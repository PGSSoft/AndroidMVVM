package com.pgssoft.mvvm.services.mock;

import com.pgssoft.mvvm.model.api.ApiRate;
import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.services.interfaces.ApiService;
import com.pgssoft.mvvm.services.interfaces.ApiServiceCallback;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public class MockApiService implements ApiService {

    @Override
    public void loadRates(ApiServiceCallback<ApiTable[]> callback) {
        ApiTable[] apiTables = new ApiTable[1];
        ArrayList<ApiRate> apiRates = new ArrayList<>();

        apiRates.add(new ApiRate("USD", "dolar amerykański", 4.04));
        apiRates.add(new ApiRate("EUR", "duro", 4.32));
        apiRates.add(new ApiRate("CHF", "frank szwajcarski", 4.05));
        apiRates.add(new ApiRate("GBP", "funt szterling", 4.05));
        apiRates.add(new ApiRate("UAH", "hrywna (Ukraina)", 0.15));
        apiRates.add(new ApiRate("CZK", "korona czeska", 0.16));

        apiTables[0] = new ApiTable("A", "020/A/NBP/2017", "2017-01-15", apiRates);

        callback.onSuccess(apiTables);
    }
}
