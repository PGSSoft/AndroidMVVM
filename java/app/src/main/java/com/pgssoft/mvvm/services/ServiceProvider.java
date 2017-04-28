package com.pgssoft.mvvm.services;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pgssoft.mvvm.services.api.RetrofitService;
import com.pgssoft.mvvm.services.database.DBHandler;
import com.pgssoft.mvvm.services.interfaces.ApiService;
import com.pgssoft.mvvm.services.interfaces.MapperService;
import com.pgssoft.mvvm.services.interfaces.Repository;
import com.pgssoft.mvvm.services.mock.ObjectMapper;

/**
 * Created by bstokrocki on 29.01.2017.
 */
public class ServiceProvider {
    private final ApiService apiService;
    private final Repository repository;
    private final MapperService mapperService;
    private final SchedulerService schedulerService;

    public ServiceProvider(Context applicationContext) {
        schedulerService = new SchedulerService();
        mapperService = new ObjectMapper();
        apiService = new RetrofitService();
        repository = new DBHandler(applicationContext, mapperService);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(applicationContext));
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

    public SchedulerService getSchedulerService() {
        return schedulerService;
    }
}
