package com.pgssoft.mvvm.views.adapters.interfaces.providers;

import com.pgssoft.mvvm.model.database.Rate;

import java.util.List;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public interface BaseItemProvider<TItem> {
    public List<TItem> getItems();
}
