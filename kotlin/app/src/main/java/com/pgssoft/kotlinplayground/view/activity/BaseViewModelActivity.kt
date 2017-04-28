package com.pgssoft.kotlinplayground.view.activity

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.pgssoft.kotlinplayground.R
import com.pgssoft.kotlinplayground.viewmodel.BaseActivityViewModel
import com.pgssoft.kotlinplayground.viewmodel.iface.IBaseActivityAccess

/**
 * Created by bstokrocki on 16.02.2017.
 */

abstract class BaseViewModelActivity<T : BaseActivityViewModel> : AppCompatActivity(), IBaseActivityAccess {
    private var progressDialog: ProgressDialog? = null
    abstract var viewModel: T

    override fun showProgressIndication(cancelable: Boolean) {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.title_loading), true, cancelable)
    }

    override fun hideProgressIndication() {
        progressDialog?.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
