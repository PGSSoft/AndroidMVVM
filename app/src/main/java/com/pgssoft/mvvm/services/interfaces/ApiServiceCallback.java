package com.pgssoft.mvvm.services.interfaces;

import android.support.annotation.Nullable;

/**
 * Created by bstokrocki on 30.01.2017.
 */
public interface ApiServiceCallback<T> {
    void onSuccess(T response);

    void onFailure(Throwable t);
}
