package com.pgssoft.mvvm.services;

import android.content.Context;

import com.pgssoft.mvvm.services.api.RetrofitService;
import com.pgssoft.mvvm.services.database.DBHandler;
import com.pgssoft.mvvm.services.interfaces.ApiService;
import com.pgssoft.mvvm.services.interfaces.MapperService;
import com.pgssoft.mvvm.services.interfaces.Repository;
import com.pgssoft.mvvm.services.mock.MockRepository;
import com.pgssoft.mvvm.services.mock.ObjectMapper;

/**
 * Created by bstokrocki on 29.01.2017.
 */
public class ServiceProvider {
    private ApiService apiService;
    private Repository repository;
    private MapperService mapperService;

    public ServiceProvider(Context applicationContext) {
        mapperService = new ObjectMapper();
        apiService = new RetrofitService();
        repository = new DBHandler(applicationContext, mapperService);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public Repository getRepository() {
        return repository;
    }

    public MapperService getMapperService() {
        return mapperService;
    }
}
