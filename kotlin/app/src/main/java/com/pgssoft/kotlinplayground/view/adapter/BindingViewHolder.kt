package com.pgssoft.kotlinplayground.view.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.pgssoft.kotlinplayground.viewmodel.BaseListItemViewModel

/**
 * Created by bstokrocki on 07.02.2017.
 */

class BindingViewHolder<TData, TViewModel : BaseListItemViewModel<TData>>(
        val viewModel: TViewModel,
        binding: ViewDataBinding,
        variable: Int) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.setVariable(variable, viewModel)
    }
}
