package com.pgssoft.mvvm.views.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pgssoft.kotlinplayground.view.adapter.BindingViewHolder
import com.pgssoft.kotlinplayground.viewmodel.BaseListItemViewModel

/**
 * Created by bstokrocki on 30.01.2017.
 */
abstract class BindingRecyclerAdapter<TData, TViewModel : BaseListItemViewModel<TData>>(
        private val context: Context,
        protected var dataItems: List<TData>,
        private val variable: Int,
        private val layoutId: Int) : RecyclerView.Adapter<BindingViewHolder<TData, TViewModel>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<TData, TViewModel> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context),
                layoutId, parent, false)

        return BindingViewHolder(createViewModel(), binding, variable)
    }

    override fun onBindViewHolder(holder: BindingViewHolder<TData, TViewModel>, position: Int) {
        val dataItem = dataItems[position]
        holder.viewModel.setData(dataItem)
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }

    abstract fun createViewModel(): TViewModel
}
