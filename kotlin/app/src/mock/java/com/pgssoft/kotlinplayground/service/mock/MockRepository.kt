package com.pgssoft.kotlinplayground.service.mock

import com.pgssoft.kotlinplayground.model.RateDto
import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.service.iface.Repository

/**
 * Created by bstokrocki on 21-Apr-17.
 */

class MockRepository : Repository {
    var saveTablesCount = 0
    var getAllRatesCount = 0
    var findRateCount = 0

    var rates = listOf(
            Rate("USD", null, "dolar amerykański", 4.03),
            Rate("AUD", null, "dolar australijski", 4.03),
            Rate("HKD", null, "dolar Hongkongu", 4.03),
            Rate("CAD", null, "dolar kanadyjski", 4.03),
            Rate("GBP", null, "funt szterling", 4.03),
            Rate("UAH", null, "hrywna (Ukraina)", 4.03),
            Rate("JPY", null, "jen (Japonia)", 4.03),
            Rate("CZK", null, "korona czeska", 4.03),
            Rate("MYR", null, "ringgit (Malezja)", 4.03)
    )

    override fun saveTables(apiTables: Array<ApiTable>) {
        saveTablesCount++
    }

    override fun getAllRates(): List<Rate> {
        getAllRatesCount++
        return rates
    }

    override fun findRateById(rateId: String): RateDto? {
        findRateCount++
        val rate = rates.find { it.currencyCode == rateId }

        return if (rate != null) RateDto(rate.currencyCode, rate.currencyName, rate.rate) else null
    }
}
