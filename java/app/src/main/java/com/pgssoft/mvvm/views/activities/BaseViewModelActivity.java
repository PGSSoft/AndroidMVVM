package com.pgssoft.mvvm.views.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pgssoft.mvvm.R;
import com.pgssoft.mvvm.viewmodels.BaseActivityViewModel;
import com.pgssoft.mvvm.viewmodels.interfaces.IBaseActivityAccess;

/**
 * Created by bstokrocki on 16.02.2017.
 */

public abstract class BaseViewModelActivity<T extends BaseActivityViewModel>
        extends AppCompatActivity implements IBaseActivityAccess {
    private ProgressDialog progressDialog;
    protected T viewModel;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (viewModel != null) {
            viewModel.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (viewModel != null) {
            viewModel.onStop();
        }
    }
}
