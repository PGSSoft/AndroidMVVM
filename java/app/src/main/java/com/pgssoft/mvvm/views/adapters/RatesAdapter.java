package com.pgssoft.mvvm.views.adapters;

import android.content.Context;

import com.pgssoft.mvvm.BR;
import com.pgssoft.mvvm.R;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.viewmodels.RateViewModel;
import com.pgssoft.mvvm.views.adapters.interfaces.handlers.RateItemHandler;
import com.pgssoft.mvvm.views.adapters.interfaces.providers.BaseItemProvider;

import java.util.List;

/**
 * Created by bstokrocki on 07.02.2017.
 */

public class RatesAdapter extends BindingRecyclerAdapter<Rate, RateViewModel> {
    private RateItemHandler rateItemHandler;

    public RatesAdapter(Context context, BaseItemProvider<Rate> itemProvider, RateItemHandler rateItemHandler) {
        super(context, itemProvider.getItems(), BR.viewModel, R.layout.list_item_rate);
        this.rateItemHandler = rateItemHandler;
    }


    public RateViewModel createViewModel() {
        return new RateViewModel(rateItemHandler);
    }
}
