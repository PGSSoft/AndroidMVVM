package com.pgssoft.mvvm.models.dto;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.pgssoft.mvvm.BR;

/**
 * Created by bstokrocki on 19.02.2017.
 */

public class RateDto extends BaseObservable {
    private String currencyCode;
    private String name;
    private double rate;

    public RateDto(String currencyCode, String name, double rate) {
        this.currencyCode = currencyCode;
        this.name = name;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getName() {
        return name;
    }

    @Bindable
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
        notifyPropertyChanged(BR.rate);
    }
}
