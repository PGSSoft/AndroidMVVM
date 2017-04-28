package com.pgssoft.mvvm;

import android.app.Application;

import com.pgssoft.mvvm.services.ServiceProvider;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by bstokrocki on 29.01.2017.
 */
public class MVVMApplication extends Application {
    private static MVVMApplication instance;

    private ServiceProvider serviceProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        JodaTimeAndroid.init(instance);

        serviceProvider = new ServiceProvider(instance);
    }

    public static ServiceProvider getServiceProvider() {
        return instance.serviceProvider;
    }
}
