package com.pgssoft.kotlinplayground.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.pgssoft.kotlinplayground.KotlinApp
import com.pgssoft.kotlinplayground.R
import com.pgssoft.kotlinplayground.databinding.ActivityRateBinding
import com.pgssoft.kotlinplayground.viewmodel.RateActivityViewModel
import com.pgssoft.kotlinplayground.viewmodel.iface.IRateActivityAccess

/**
 * Created by bstokrocki on 31.01.2017.
 */
class RateActivity : BaseViewModelActivity<RateActivityViewModel>(), IRateActivityAccess {
    companion object {
        val EXTRA_RATE_ID = "EXTRA_RATE_ID"

        fun prepareStartIntent(context: Context, rateCurrencyCode: String): Intent {
            val intent = Intent(context, RateActivity::class.java)
            intent.putExtra(EXTRA_RATE_ID, rateCurrencyCode)

            return intent
        }
    }

    override lateinit var viewModel: RateActivityViewModel
    private lateinit var binding: ActivityRateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.label_calculator)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate)

        val rateId = intent.getStringExtra(EXTRA_RATE_ID)
        val serviceProvider = KotlinApp.serviceProvider
        viewModel = RateActivityViewModel(serviceProvider.scheduler, serviceProvider.repository, rateId)

        binding.viewModel = viewModel
    }
}
