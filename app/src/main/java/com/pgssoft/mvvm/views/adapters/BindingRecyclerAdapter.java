package com.pgssoft.mvvm.views.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pgssoft.mvvm.viewmodels.BaseListItemViewModel;

import java.util.List;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public abstract class BindingRecyclerAdapter<TData, TViewModel extends BaseListItemViewModel<TData>>
        extends RecyclerView.Adapter<BindingViewHolder<TViewModel>> {
    private Context context;
    private List<TData> dataItems;
    private final int variable;
    private final int layoutId;

    public BindingRecyclerAdapter(Context context, List<TData> dataItems, int variable, int layoutId) {
        this.context = context;
        this.dataItems = dataItems;
        this.variable = variable;
        this.layoutId = layoutId;
    }

    @Override
    public BindingViewHolder<TViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                layoutId, parent, false);

        return new BindingViewHolder<>(binding, createViewModel(), variable);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<TViewModel> holder, int position) {
        TData dataItem = dataItems.get(position);
        holder.getViewModel().setData(dataItem);
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public abstract TViewModel createViewModel();
}
