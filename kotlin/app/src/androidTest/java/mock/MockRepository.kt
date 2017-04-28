package mock

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

    override fun saveTables(apiTables: Array<ApiTable>) {
        saveTablesCount++
    }

    override fun getAllRates(): List<Rate> {
        getAllRatesCount++
        return ArrayList()
    }

    override fun findRateById(rateId: String): RateDto? {
        findRateCount++
        return null
    }
}
