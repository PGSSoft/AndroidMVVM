package com.pgssoft.kotlinplayground.service.iface

/**
 * Created by bstokrocki on 30.01.2017.
 */
interface ApiServiceCallback<T> {
    fun onSuccess(response: T)

    fun onFailure(t: Throwable)
}
