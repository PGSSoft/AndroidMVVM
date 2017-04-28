package com.pgssoft.kotlinplayground.viewmodel.iface

/**
 * Created by bstokrocki on 16.02.2017.
 */

interface IBaseActivityAccess {
    fun showProgressIndication(cancelable: Boolean)

    fun hideProgressIndication()
}
