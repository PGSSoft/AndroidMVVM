package com.pgssoft.kotlinplayground.service.mock

import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.service.iface.ApiService
import com.pgssoft.kotlinplayground.service.iface.ApiServiceCallback

/**
 * Created by bstokrocki on 21-Apr-17.
 */
class MockApi(var success: Boolean = true) : ApiService {
    var loadRatesCounter = 0
    var successCallback: (() -> Unit)? = null
    var failureCallback: (() -> Unit)? = null

    override fun loadRates(callback: ApiServiceCallback<Array<ApiTable>>) {
        loadRatesCounter++
        if (success) {
            callback.onSuccess(arrayOf())
            successCallback?.invoke()
        } else {
            callback.onFailure(Throwable())
            failureCallback?.invoke()
        }
    }
}