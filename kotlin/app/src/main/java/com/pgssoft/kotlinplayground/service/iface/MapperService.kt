package com.pgssoft.kotlinplayground.service.iface

import com.pgssoft.kotlinplayground.model.RateDto
import com.pgssoft.kotlinplayground.model.api.ApiRate
import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.model.db.Table

/**
 * Created by bstokrocki on 31.01.2017.
 */
interface MapperService {
    fun map(apiRate: ApiRate): Rate

    fun map(apiTable: ApiTable): Table

    fun map(rate: Rate): RateDto
}
