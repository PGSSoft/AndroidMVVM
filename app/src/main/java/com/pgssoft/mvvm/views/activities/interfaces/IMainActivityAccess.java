package com.pgssoft.mvvm.views.activities.interfaces;

import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.views.adapters.interfaces.handlers.RateItemHandler;
import com.pgssoft.mvvm.views.adapters.interfaces.providers.BaseItemProvider;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public interface IMainActivityAccess {
    void displayRates(BaseItemProvider<Rate> ratesProvider, RateItemHandler rateItemHandler);

    void showProgressIndicator();

    void hideProgressIndicator();

    void openRateDetailsScreen(Rate rate);
}
