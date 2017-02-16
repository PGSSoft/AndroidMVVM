package com.pgssoft.mvvm.viewmodels.interfaces;

/**
 * Created by bartooo on 16.02.2017.
 */

public interface IBaseActivityAccess {
    void showProgressIndication(boolean cancelable);

    void hideProgressIndication();
}
