package mock

import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.view.adapter.providers.BaseItemProvider
import com.pgssoft.kotlinplayground.viewmodel.iface.IMainActivityAccess

/**
 * Created by bstokrocki on 09-May-17.
 */
class MockMainActivityAccess : IMainActivityAccess {
    var showProgressCounter = 0
    var hideProgressCounter = 0
    var displayRatesCounter = 0
    var openRateDetailsCounter = 0

    override fun showProgressIndication(cancelable: Boolean) {
        showProgressCounter++
    }

    override fun hideProgressIndication() {
        hideProgressCounter++
    }

    override fun displayRates(ratesProvider: BaseItemProvider<Rate>) {
        displayRatesCounter++
    }

    override fun openRateDetailsScreen(rate: Rate) {
        openRateDetailsCounter
    }
}