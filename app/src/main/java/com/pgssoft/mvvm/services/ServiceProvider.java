package com.pgssoft.mvvm.services;

import android.content.Context;

import com.pgssoft.mvvm.services.interfaces.ApiService;
import com.pgssoft.mvvm.services.interfaces.MapperService;
import com.pgssoft.mvvm.services.interfaces.Repository;
import com.pgssoft.mvvm.services.mock.MockApiService;
import com.pgssoft.mvvm.services.mock.MockRepository;

/**
 * Created by bstokrocki on 29.01.2017.
 */
public class ServiceProvider {
    private ApiService apiService;
    private Repository repository;
    private MapperService mapperService;

    public ServiceProvider(Context applicationContext) {
        apiService = new MockApiService();
        repository = new MockRepository();
        mapperService = new ObjectMapper();
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
