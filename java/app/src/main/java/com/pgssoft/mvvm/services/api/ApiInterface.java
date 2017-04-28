package com.pgssoft.mvvm.services.api;

import com.pgssoft.mvvm.model.api.ApiTable;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public interface ApiInterface {
    @GET("exchangerates/tables/A?format=json")
    Call<ApiTable[]> getRates();
}
