package com.pgssoft.mvvm.viewmodels;

import android.databinding.Observable;
import android.databinding.ObservableField;

import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.services.interfaces.Repository;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public class RateActivityViewModel {
    private final Repository repository;
    private final Rate rate;
    private final int decimalRate;

    private final ObservableField<Integer> plnAmount;
    private final ObservableField<Integer> currencyAmount;

    public RateActivityViewModel(Repository repository, String rateId) {
        this.repository = repository;
        this.rate = repository.findRateById(rateId);

        decimalRate = (int) (rate.getRate() * 100);
        plnAmount = new ObservableField<>(100);
        currencyAmount = new ObservableField<>(100 / decimalRate);

        currencyAmount.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                int newValue = (currencyAmount.get() * decimalRate) / 100 + (currencyAmount.get() % decimalRate) / 100;
                if (!plnAmount.get().equals(newValue)) {
                    plnAmount.set(newValue);
                }
            }
        });

        plnAmount.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                int newValue = (plnAmount.get() * 100) / decimalRate;
                if (!currencyAmount.get().equals(newValue)) {
                    currencyAmount.set(newValue);
                }
            }
        });
    }

    public ObservableField<Integer> getCurrencyAmount() {
        return currencyAmount;
    }

    public ObservableField<Integer> getPlnAmount() {
        return plnAmount;
    }

    public Rate getRate() {
        return rate;
    }
}
