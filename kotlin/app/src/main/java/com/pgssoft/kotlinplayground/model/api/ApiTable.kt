package com.pgssoft.kotlinplayground.model.api

import com.google.gson.annotations.SerializedName

/**
 * Created by bstokrocki on 30.01.2017.
 */
class ApiTable(@SerializedName("table") val id: String,
               @SerializedName("no") val number: String,
               @SerializedName("effectiveDate") val lastUpdate: String,
               @SerializedName("rates") val apiRates: List<ApiRate>)
