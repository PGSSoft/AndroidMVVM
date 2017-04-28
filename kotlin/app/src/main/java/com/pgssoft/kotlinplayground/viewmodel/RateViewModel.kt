package com.pgssoft.kotlinplayground.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.view.adapter.handlers.RateItemHandler
import java.util.*

/**
 * Created by bstokrocki on 30.01.2017.
 */
class RateViewModel(private val itemHandler: RateItemHandler) : BaseListItemViewModel<Rate>() {
    val rate = ObservableField<Rate>()
    val isGoingUp = ObservableBoolean(Random().nextBoolean())

    override fun setData(rate: Rate) {
        this.rate.set(rate)
    }

    fun itemClicked() {
        itemHandler.showRateDetails(rate.get())
    }
}
