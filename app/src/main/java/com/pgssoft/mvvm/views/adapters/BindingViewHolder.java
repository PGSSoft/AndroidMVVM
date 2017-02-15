package com.pgssoft.mvvm.views.adapters;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.pgssoft.mvvm.viewmodels.BaseListItemViewModel;

/**
 * Created by bstokrocki on 07.02.2017.
 */

public class BindingViewHolder<TViewModel extends BaseListItemViewModel>
        extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    private TViewModel viewModel;

    public BindingViewHolder(ViewDataBinding binding, TViewModel viewModel, int variable) {
        super(binding.getRoot());

        this.binding = binding;
        this.viewModel = viewModel;

        binding.setVariable(variable, viewModel);
    }

    public TViewModel getViewModel() {
        return viewModel;
    }
}
