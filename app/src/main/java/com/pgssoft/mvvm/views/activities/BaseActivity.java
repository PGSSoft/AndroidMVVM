package com.pgssoft.mvvm.views.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.pgssoft.mvvm.R;
import com.pgssoft.mvvm.viewmodels.interfaces.IBaseActivityAccess;

/**
 * Created by bstokrocki on 16.02.2017.
 */

public class BaseActivity extends AppCompatActivity implements IBaseActivityAccess {
    private ProgressDialog progressDialog;

    @Override
    public void showProgressIndication(boolean cancelable) {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.title_loading), true, cancelable);
    }

    @Override
    public void hideProgressIndication() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }
}
