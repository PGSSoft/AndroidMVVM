package com.pgssoft.mvvm.views.adapters.interfaces.providers;

import android.databinding.ObservableField;

import com.pgssoft.mvvm.views.adapters.RatesAdapter;

/**
 * Created by bstokrocki on 07.02.2017.
 */

public interface RatesAdapterProvider {
    ObservableField<RatesAdapter> getAdapter();
}
