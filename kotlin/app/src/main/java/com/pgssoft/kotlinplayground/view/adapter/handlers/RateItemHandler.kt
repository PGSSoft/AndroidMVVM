package com.pgssoft.kotlinplayground.view.adapter.handlers

import com.pgssoft.kotlinplayground.model.db.Rate

/**
 * Created by bstokrocki on 31.01.2017.
 */
interface RateItemHandler {
    fun showRateDetails(rate: Rate)
}
