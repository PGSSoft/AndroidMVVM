package com.pgssoft.mvvm.viewmodels;

import android.databinding.ObservableField;

import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.views.adapters.interfaces.handlers.RateItemHandler;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public class RateViewModel extends BaseListItemViewModel<Rate>{
    private RateItemHandler itemHandler;
    private Rate rate;

    private final ObservableField<String> rateName;

    public RateViewModel(RateItemHandler itemHandler) {
        this.itemHandler = itemHandler;

        rateName = new ObservableField<>();
    }

    @Override
    public void setData(Rate rate) {
        this.rate = rate;

        rateName.set(rate.getCurrencyCode());
    }

    public void itemClicked() {
        itemHandler.showRateDetails(rate);
    }

    public ObservableField<String> getRateName() {
        return rateName;
    }
}
