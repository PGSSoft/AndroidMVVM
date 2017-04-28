package com.pgssoft.kotlinplayground.model.api

import com.google.gson.annotations.SerializedName

class ApiRate(@SerializedName("code") val currencyCode: String,
              @SerializedName("currency") val currencyName: String,
              @SerializedName("mid") val rate: Double)
