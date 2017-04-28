package com.pgssoft.kotlinplayground.view.adapter

import android.content.Context
import com.pgssoft.kotlinplayground.BR
import com.pgssoft.kotlinplayground.R
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.view.adapter.handlers.RateItemHandler
import com.pgssoft.kotlinplayground.view.adapter.providers.BaseItemProvider
import com.pgssoft.kotlinplayground.viewmodel.RateViewModel
import com.pgssoft.mvvm.views.adapters.BindingRecyclerAdapter

/**
 * Created by bstokrocki on 07.02.2017.
 */

class RatesAdapter(context: Context, itemProvider: BaseItemProvider<Rate>, private val rateItemHandler: RateItemHandler)
    : BindingRecyclerAdapter<Rate, RateViewModel>(context, itemProvider.items, BR.viewModel, R.layout.list_item_rate) {

    override fun createViewModel(): RateViewModel {
        return RateViewModel(rateItemHandler)
    }

    fun filter(itemProvider: BaseItemProvider<Rate>) {
        dataItems = itemProvider.items
        notifyDataSetChanged()
    }
}
