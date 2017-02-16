package com.pgssoft.mvvm.services.api;

import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.services.exceptions.ApiRequestFailureError;
import com.pgssoft.mvvm.services.interfaces.ApiService;
import com.pgssoft.mvvm.services.interfaces.ApiServiceCallback;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bstokrocki on 29.01.2017.
 */
public class RetrofitService implements ApiService {
    private Retrofit retrofit;
    private ApiInterface apiInterface;

    public RetrofitService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nbp.pl/api/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    @Override
    public void loadRates(final ApiServiceCallback<ApiTable[]> callback) {
        Call<ApiTable[]> call = apiInterface.getRates();
        call.enqueue(new BaseCallback<>(callback));
    }

    private class BaseCallback<T> implements Callback<T> {
        private ApiServiceCallback<T> serviceCallback;

        public BaseCallback(ApiServiceCallback<T> serviceCallback) {
            this.serviceCallback = serviceCallback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                serviceCallback.onSuccess(response.body());
            } else {
                serviceCallback.onFailure(new ApiRequestFailureError(response.code(),
                        prepareErrorMessage(response.errorBody())));
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            serviceCallback.onFailure(t);
        }

        private String prepareErrorMessage(ResponseBody responseBody) {
            try {
                return responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
