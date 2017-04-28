package com.pgssoft.kotlinplayground.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.pgssoft.kotlinplayground.KotlinApp
import com.pgssoft.kotlinplayground.R
import com.pgssoft.kotlinplayground.databinding.ActivityMainBinding
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.view.adapter.RatesAdapter
import com.pgssoft.kotlinplayground.view.adapter.providers.BaseItemProvider
import com.pgssoft.kotlinplayground.view.adapter.providers.RatesAdapterProvider
import com.pgssoft.kotlinplayground.viewmodel.MainActivityViewModel
import com.pgssoft.kotlinplayground.viewmodel.iface.IMainActivityAccess

class MainActivity : BaseViewModelActivity<MainActivityViewModel>(), IMainActivityAccess, RatesAdapterProvider {
    override lateinit var viewModel: MainActivityViewModel
    override lateinit var adapter: RatesAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val serviceProvider = KotlinApp.serviceProvider
        viewModel = MainActivityViewModel(this, serviceProvider.apiService, serviceProvider.repository)
        adapter = RatesAdapter(this, viewModel, viewModel)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.viewModel = viewModel
        binding.adapterProvider = this

        viewModel.onInfrastructureReady()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        searchView.setOnCloseListener({
            viewModel.clearFiltering()
            false
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun displayRates(ratesProvider: BaseItemProvider<Rate>) {
        adapter.filter(ratesProvider)
    }

    override fun openRateDetailsScreen(rate: Rate) {
        startActivity(RateActivity.prepareStartIntent(this, rate.currencyCode))
    }
}
