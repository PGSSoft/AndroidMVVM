package com.pgssoft.kotlinplayground.service.api

import com.pgssoft.kotlinplayground.model.api.ApiTable
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by bstokrocki on 30.01.2017.
 */
interface ApiInterface {
    @GET("exchangerates/tables/A?format=json")
    fun getRates(): Call<Array<ApiTable>>
}
