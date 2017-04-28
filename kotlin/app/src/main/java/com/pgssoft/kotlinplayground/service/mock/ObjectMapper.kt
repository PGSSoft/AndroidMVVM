package com.pgssoft.kotlinplayground.service.mock

import com.pgssoft.kotlinplayground.constant.DateFormats
import com.pgssoft.kotlinplayground.model.RateDto
import com.pgssoft.kotlinplayground.model.api.ApiRate
import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.model.db.Table
import com.pgssoft.kotlinplayground.service.iface.MapperService
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Created by bstokrocki on 31.01.2017.
 */
class ObjectMapper : MapperService {
    override fun map(apiRate: ApiRate): Rate {
        return Rate(apiRate.currencyCode, null, apiRate.currencyName, apiRate.rate)
    }

    override fun map(apiTable: ApiTable): Table {
        return Table(apiTable.id, apiTable.number, DateTime.parse(apiTable.lastUpdate,
                        DateTimeFormat.forPattern(DateFormats.LAST_UPDATE_DATE_FORMAT)).toDate())
    }

    override fun map(rate: Rate): RateDto {
        return RateDto(rate.currencyCode, rate.currencyName, rate.rate)
    }
}
