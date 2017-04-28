package com.pgssoft.kotlinplayground.service.api

import com.pgssoft.kotlinplayground.model.api.ApiTable
import com.pgssoft.kotlinplayground.service.exception.ApiRequestFailureError
import com.pgssoft.kotlinplayground.service.iface.ApiService
import com.pgssoft.kotlinplayground.service.iface.ApiServiceCallback
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by bstokrocki on 29.01.2017.
 */
class RetrofitService : ApiService {
    val apiInterface: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.nbp.pl/api/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    override fun loadRates(callback: ApiServiceCallback<Array<ApiTable>>) {
        val call = apiInterface.getRates()
        call.enqueue(BaseCallback(callback))
    }

    private inner class BaseCallback<T>(private val serviceCallback: ApiServiceCallback<T>) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                serviceCallback.onSuccess(response.body())
            } else {
                serviceCallback.onFailure(ApiRequestFailureError(response.code(),
                        prepareErrorMessage(response.errorBody())))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            serviceCallback.onFailure(t)
        }

        private fun prepareErrorMessage(responseBody: ResponseBody): String {
            try {
                return responseBody.string()
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }

        }
    }
}
