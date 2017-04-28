package com.pgssoft.kotlinplayground.service.exception

/**
 * Created by bstokrocki on 31.01.2017.
 */
class ApiRequestFailureError(val statusCode: Int, message: String) : Throwable(message)
