package com.pgssoft.kotlinplayground.viewmodel

/**
 * Created by bstokrocki on 31.01.2017.
 */
abstract class BaseListItemViewModel<TData> {
    abstract fun setData(data: TData)
}
