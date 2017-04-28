package com.pgssoft.kotlinplayground.viewmodel

import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.service.iface.ApiService
import com.pgssoft.kotlinplayground.service.iface.ApiServiceCallback
import com.pgssoft.kotlinplayground.service.iface.Repository
import com.pgssoft.kotlinplayground.view.adapter.handlers.RateItemHandler
import com.pgssoft.kotlinplayground.view.adapter.providers.BaseItemProvider
import com.pgssoft.kotlinplayground.viewmodel.iface.IMainActivityAccess

/**
 * Created by bstokrocki on 29.01.2017.
 */
class MainActivityViewModel(
        private val activityAccess: IMainActivityAccess,
        private val apiService: ApiService,
        private val repository: Repository) : BaseActivityViewModel(), BaseItemProvider<Rate>, RateItemHandler {

    override var items: List<Rate> = ArrayList()
    internal var allItems: List<Rate> = ArrayList()

    val logoUrl = "https://raw.githubusercontent.com/PGSSoft/AndroidMVVM/master/app/src/main/res/drawable-xxhdpi/pgs.png"

    fun onInfrastructureReady() {
        loadRates()
    }

    internal fun loadRates() {
        activityAccess.showProgressIndication(false)
        apiService.loadRates(object : ApiServiceCallback<Array<ApiTable>> {
            override fun onSuccess(apiTables: Array<ApiTable>) {
                repository.saveTables(apiTables)
                allItems = repository.getAllRates()
                clearFiltering()
                activityAccess.hideProgressIndication()
            }

            override fun onFailure(t: Throwable) {
                activityAccess.hideProgressIndication()
            }
        })
    }

    fun clearFiltering() {
        items = allItems
        activityAccess.displayRates(this@MainActivityViewModel)
    }

    fun filter(query: String) {
        items = allItems.filter { it.currencyName.contains(query, true) || it.currencyCode.contains(query, true) }
        activityAccess.displayRates(this@MainActivityViewModel)
    }

    override fun showRateDetails(rate: Rate) {
        activityAccess.openRateDetailsScreen(rate)
    }

    override fun onStart() {

    }

    override fun onStop() {

    }
}
