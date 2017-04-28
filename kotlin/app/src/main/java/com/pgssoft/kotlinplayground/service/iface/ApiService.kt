package com.pgssoft.kotlinplayground.service.iface

import com.pgssoft.kotlinplayground.model.api.ApiTable

/**
 * Created by bstokrocki on 29.01.2017.
 */
interface ApiService {
    fun loadRates(callback: ApiServiceCallback<Array<ApiTable>>)
}
