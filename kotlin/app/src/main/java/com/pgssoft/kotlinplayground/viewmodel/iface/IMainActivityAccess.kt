package com.pgssoft.kotlinplayground.viewmodel.iface

import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.view.adapter.providers.BaseItemProvider

/**
 * Created by bstokrocki on 30.01.2017.
 */
interface IMainActivityAccess : IBaseActivityAccess {
    fun displayRates(ratesProvider: BaseItemProvider<Rate>)

    fun openRateDetailsScreen(rate: Rate)
}
