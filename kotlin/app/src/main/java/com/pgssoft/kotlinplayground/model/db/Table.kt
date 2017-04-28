package com.pgssoft.kotlinplayground.model.db

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.util.*

/**
 * Created by bstokrocki on 30.01.2017.
 */
@DatabaseTable
class Table() {
    companion object {
        const val FIELD_ID = "id"
        const val FIELD_NUMBER = "number"
        const val FIELD_LAST_UPDATE = "lastUpdate"
    }

    @DatabaseField(columnName = FIELD_ID, id = true)
    var id: String = ""

    @DatabaseField(columnName = FIELD_NUMBER, canBeNull = false)
    var number: String = ""

    @DatabaseField(columnName = FIELD_LAST_UPDATE, canBeNull = false, dataType = DataType.DATE_STRING)
    var lastUpdate: Date? = null

    constructor(id: String, number: String, lastUpdate: Date) : this() {
        this.id = id
        this.number = number
        this.lastUpdate = lastUpdate
    }
}
