package com.pgssoft.mvvm.viewmodels;

import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;
import com.pgssoft.mvvm.services.interfaces.ApiService;
import com.pgssoft.mvvm.services.interfaces.ApiServiceCallback;
import com.pgssoft.mvvm.services.interfaces.MapperService;
import com.pgssoft.mvvm.services.interfaces.Repository;
import com.pgssoft.mvvm.viewmodels.interfaces.IMainActivityAccess;
import com.pgssoft.mvvm.views.adapters.interfaces.handlers.RateItemHandler;
import com.pgssoft.mvvm.views.adapters.interfaces.providers.BaseItemProvider;

import java.util.List;

/**
 * Created by bstokrocki on 29.01.2017.
 */
public class MainActivityViewModel implements BaseItemProvider<Rate>, RateItemHandler {
    private final IMainActivityAccess activityAccess;
    private final ApiService apiService;
    private final Repository repository;

    private List<Rate> rates;

    public MainActivityViewModel(IMainActivityAccess activityAccess, ApiService apiService,
                                 Repository repository) {
        this.activityAccess = activityAccess;
        this.apiService = apiService;
        this.repository = repository;
    }

    public void onInfrastructureReady() {
        loadRates();
    }

    private void loadRates() {
        activityAccess.showProgressIndication(false);
        apiService.loadRates(new ApiServiceCallback<ApiTable[]>() {
            @Override
            public void onSuccess(ApiTable[] apiTables) {
                repository.saveTables(apiTables);
                rates = repository.getAllRates();

                activityAccess.displayRates(MainActivityViewModel.this, MainActivityViewModel.this);
                activityAccess.hideProgressIndication();
            }

            @Override
            public void onFailure(Throwable t) {
                activityAccess.hideProgressIndication();
            }
        });
    }

    @Override
    public List<Rate> getItems() {
        return rates;
    }

    @Override
    public void showRateDetails(Rate rate) {
        activityAccess.openRateDetailsScreen(rate);
    }
}
