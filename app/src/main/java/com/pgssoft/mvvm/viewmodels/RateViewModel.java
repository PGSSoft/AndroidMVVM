package com.pgssoft.mvvm.viewmodels;

import android.databinding.ObservableField;

import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.views.adapters.interfaces.handlers.RateItemHandler;

import java.util.Random;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public class RateViewModel extends BaseListItemViewModel<Rate>{
    private RateItemHandler itemHandler;

    private ObservableField<Rate> rate;
    private final ObservableField<Boolean> isGoingUp;

    public RateViewModel(RateItemHandler itemHandler) {
        this.itemHandler = itemHandler;

        rate = new ObservableField<>();
        isGoingUp = new ObservableField<>(new Random().nextBoolean());
    }

    @Override
    public void setData(Rate rate) {
        this.rate.set(rate);
    }

    public ObservableField<Rate> getRate() {
        return rate;
    }

    public void itemClicked() {
        itemHandler.showRateDetails(rate.get());
    }

    public ObservableField<Boolean> getIsGoingUp() {
        return isGoingUp;
    }
}
