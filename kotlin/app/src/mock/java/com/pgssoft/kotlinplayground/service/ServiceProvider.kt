package com.pgssoft.kotlinplayground.service

import android.content.Context
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.pgssoft.kotlinplayground.service.mock.MockApi
import com.pgssoft.kotlinplayground.service.mock.MockRepository
import com.pgssoft.kotlinplayground.service.mock.ObjectMapper
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by bstokrocki on 13-Apr-17.
 */
class ServiceProvider(applicationContext: Context) {
    val scheduler = SchedulerService()
    val mapper = ObjectMapper()
    val repository = MockRepository()
    val apiService = MockApi()

    init {
        JodaTimeAndroid.init(applicationContext)
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(applicationContext))
    }
}