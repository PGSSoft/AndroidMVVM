package com.pgssoft.mvvm.viewmodels.interfaces;

import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.views.adapters.interfaces.handlers.RateItemHandler;
import com.pgssoft.mvvm.views.adapters.interfaces.providers.BaseItemProvider;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public interface IMainActivityAccess extends IBaseActivityAccess {
    void displayRates(BaseItemProvider<Rate> ratesProvider, RateItemHandler rateItemHandler);

    void openRateDetailsScreen(Rate rate);
}
