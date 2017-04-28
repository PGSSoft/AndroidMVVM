package com.pgssoft.kotlinplayground.service.iface


import com.pgssoft.kotlinplayground.model.RateDto
import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.model.db.Rate

/**
 * Created by bstokrocki on 30.01.2017.
 */
interface Repository {
    fun saveTables(apiTables: Array<ApiTable>)

    fun getAllRates(): List<Rate>

    fun findRateById(rateId: String): RateDto?
}
