package com.pgssoft.kotlinplayground.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.pgssoft.kotlinplayground.BR

/**
 * Created by bstokrocki on 19.02.2017.
 */

class RateDto(var currencyCode: String, var name: String, rate: Double) : BaseObservable() {
    var rate = rate
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.rate)
        }
}
