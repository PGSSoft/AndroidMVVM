package com.pgssoft.kotlinplayground.service

import android.content.Context
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.pgssoft.kotlinplayground.service.api.RetrofitService
import com.pgssoft.kotlinplayground.service.db.DBHandler
import com.pgssoft.kotlinplayground.service.db.DBHelper
import com.pgssoft.kotlinplayground.service.mock.ObjectMapper
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by bstokrocki on 13-Apr-17.
 */
class ServiceProvider(applicationContext: Context) {
    val scheduler = SchedulerService()
    val mapper = ObjectMapper()
    val repository = DBHandler(DBHelper(applicationContext), mapper)
    val apiService = RetrofitService()

    init {
        JodaTimeAndroid.init(applicationContext)
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(applicationContext))
    }
}