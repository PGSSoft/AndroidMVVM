

import android.support.test.runner.AndroidJUnit4
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.view.adapter.providers.BaseItemProvider
import com.pgssoft.kotlinplayground.viewmodel.MainActivityViewModel
import com.pgssoft.kotlinplayground.viewmodel.iface.IMainActivityAccess
import mock.MockApi
import mock.MockRepository
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by bstokrocki on 21-Apr-17.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    val repo = MockRepository()
    val api = MockApi()
    val activityAccess = MockMainActivityAccess()
    val viewModel = MainActivityViewModel(activityAccess, api, repo)

    @Test
    fun testLoadRatesSuccess() {
        api.success = true
        for (i in 1..100) {
            api.successCallback = {
                assertThat(repo.saveTablesCount, equalTo(i))
                assertThat(activityAccess.displayRatesCounter, equalTo(i))
                assertThat(activityAccess.hideProgressCounter, equalTo(i))
            }

            viewModel.loadRates()
            assertThat(activityAccess.showProgressCounter, equalTo(i))
        }
    }

    @Test
    fun testLoadRatesFailure() {
        api.success = false
        for (i in 1..100) {
            api.failureCallback = {
                assertThat(repo.saveTablesCount, equalTo(0))
                assertThat(activityAccess.displayRatesCounter, equalTo(0))
                assertThat(activityAccess.hideProgressCounter, equalTo(i))
            }

            viewModel.loadRates()
            assertThat(activityAccess.showProgressCounter, equalTo(i))
        }
    }

    @Test
    fun testFiltering() {
        val mockRates = arrayOf(
                Rate("USD", null, "dolar amerykański", 4.03),
                Rate("AUD", null, "dolar australijski", 4.03),
                Rate("HKD", null, "dolar Hongkongu", 4.03),
                Rate("CAD", null, "dolar kanadyjski", 4.03),
                Rate("GBP", null, "funt szterling", 4.03),
                Rate("UAH", null, "hrywna (Ukraina)", 4.03),
                Rate("JPY", null, "jen (Japonia)", 4.03),
                Rate("CZK", null, "korona czeska", 4.03),
                Rate("MYR", null, "ringgit (Malezja)", 4.03)
        )

        innerFiltering(mockRates, "test", arrayOf())
    }

    fun innerFiltering(rates: Array<Rate>, query: String, results: Array<Rate>) {
        viewModel.clearFiltering()
        assertThat(viewModel.items.size, equalTo(viewModel.allItems.size))

        viewModel.filter(query)
        viewModel.items.size == results.size
    }

    inner class MockMainActivityAccess : IMainActivityAccess {
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
}