package com.pgssoft.kotlinplayground.service.db

import com.pgssoft.kotlinplayground.model.RateDto
import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.service.iface.MapperService
import com.pgssoft.kotlinplayground.service.iface.Repository
import java.sql.SQLException

/**
 * Created by bstokrocki on 16.02.2017.
 */

class DBHandler(private val dbHelper: DBHelper, private val mapperService: MapperService) : Repository {

    override fun saveTables(apiTables: Array<ApiTable>) {
        apiTables.map { t -> val table = mapperService.map(t)
            dbHelper.tableDao.createOrUpdate(table)

            t.apiRates.map { r -> val rate = mapperService.map(r)
                rate.table = table
                dbHelper.rateDao.createOrUpdate(rate)
            }
        }
    }

    override fun getAllRates(): List<Rate> {
        try {
            return dbHelper.rateDao.queryForAll()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun findRateById(rateId: String): RateDto? {
        try {
            val rate = dbHelper.rateDao.queryBuilder().where()
                    .eq(Rate.FIELD_ID, rateId)
                    .queryForFirst()

            return if (rate != null) mapperService.map(rate) else null
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }
}
