package com.pgssoft.mvvm.services.interfaces;

import com.pgssoft.mvvm.model.api.ApiTable;
/**
 * Created by bstokrocki on 29.01.2017.
 */
public interface ApiService {
    void loadRates(ApiServiceCallback<ApiTable[]> callback);
}
