package com.pgssoft.mvvm.views.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pgssoft.mvvm.MVVMApplication;
import com.pgssoft.mvvm.R;
import com.pgssoft.mvvm.databinding.ActivityRateBinding;
import com.pgssoft.mvvm.services.ServiceProvider;
import com.pgssoft.mvvm.viewmodels.RateActivityViewModel;
import com.pgssoft.mvvm.viewmodels.interfaces.IRateActivityAccess;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public class RateActivity extends BaseViewModelActivity<RateActivityViewModel>
        implements IRateActivityAccess {
    public static final String EXTRA_RATE_ID = "EXTRA_RATE_ID";

    private ServiceProvider serviceProvider;
    private ActivityRateBinding binding;
    private RateActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.label_calculator);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rate);

        String rateId = getIntent().getStringExtra(EXTRA_RATE_ID);
        ServiceProvider serviceProvider = MVVMApplication.getServiceProvider();

        viewModel = new RateActivityViewModel(serviceProvider.getRepository(),
                serviceProvider.getSchedulerService(), rateId);

        binding.setViewModel(viewModel);
    }

    public static Intent prepareStartIntent (Context context, String rateCurrencyCode) {
        Intent intent = new Intent(context, RateActivity.class);
        intent.putExtra(EXTRA_RATE_ID, rateCurrencyCode);

        return intent;
    }
}
