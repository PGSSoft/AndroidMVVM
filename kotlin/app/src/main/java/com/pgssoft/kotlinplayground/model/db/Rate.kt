package com.pgssoft.kotlinplayground.model.db

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class Rate() {
    companion object {
        const val FIELD_ID = "id"
        const val FIELD_TABLE = "table"
        const val FIELD_NAME = "currencyName"
        const val FIELD_RATE = "rate"
    }

    @DatabaseField(columnName = FIELD_ID, id = true)
    var currencyCode: String = ""

    @DatabaseField(columnName = FIELD_TABLE, canBeNull = false, foreign = true, foreignAutoRefresh = true)
    var table: Table? = null

    @DatabaseField(columnName = FIELD_NAME, canBeNull = false)
    var currencyName: String = ""

    @DatabaseField(columnName = FIELD_RATE, canBeNull = false)
    var rate: Double = 1.0

    constructor(currencyCode: String, table: Table?, currencyName: String, rate: Double) : this() {
        this.currencyCode = currencyCode
        this.table = table
        this.currencyName = currencyName
        this.rate = rate
    }
}
