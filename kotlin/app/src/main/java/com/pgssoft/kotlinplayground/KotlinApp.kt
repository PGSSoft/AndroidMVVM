package com.pgssoft.kotlinplayground

import android.app.Application
import com.pgssoft.kotlinplayground.service.ServiceProvider

/**
 * Created by bstokrocki on 29.01.2017.
 */
class KotlinApp : Application() {
    companion object {
        lateinit var serviceProvider: ServiceProvider
    }

    override fun onCreate() {
        super.onCreate()
        serviceProvider = ServiceProvider(this)
    }
}
