

import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.service.mock.MockApi
import com.pgssoft.kotlinplayground.service.mock.MockRepository
import com.pgssoft.kotlinplayground.viewmodel.MainActivityViewModel
import com.pgssoft.kotlinplayground.viewmodel.iface.IMainActivityAccess
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by bstokrocki on 21-Apr-17.
 */

class UnitTests {

    @Test
    fun testFiltering() {
        val repo = MockRepository()
        val api = MockApi()
        val activityAccess: IMainActivityAccess = mock(IMainActivityAccess::class.java)
        val viewModel = MainActivityViewModel(activityAccess, api, repo)

        val mockRates = repo.getAllRates()

        fun innerFiltering(query: String, results: List<Rate>) {
            viewModel.clearFiltering()
            assertEquals(viewModel.items.size, viewModel.allItems.size+10)

            viewModel.filter(query)
            assertEquals(viewModel.items.size, results.size)

            for (item in viewModel.items) {
                assertThat(results, CoreMatchers.hasItem(item))
            }
        }

        viewModel.onInfrastructureReady()

        innerFiltering("test", listOf())
        innerFiltering("ron", listOf(mockRates[7]))
        innerFiltering("RoN", listOf(mockRates[7]))
        innerFiltering("Do", mockRates.subList(0, 4))
        innerFiltering("US", listOf(mockRates[0], mockRates[1]))
        innerFiltering("(", listOf(mockRates[5], mockRates[6], mockRates[8]))
    }

    @Test
    fun testLoadRatesSuccess() {
        val repo = MockRepository()
        val api = MockApi()
        val activityAccess: IMainActivityAccess = mock(IMainActivityAccess::class.java)
        val viewModel = MainActivityViewModel(activityAccess, api, repo)

        api.success = true
        for (i in 1..10) {
            api.successCallback = {
                assertEquals(repo.saveTablesCount, i)
                verify(activityAccess, times(i)).displayRates(viewModel)
                verify(activityAccess, times(i)).hideProgressIndication()
            }

            viewModel.loadRates()
            verify(activityAccess, times(i)).showProgressIndication(false)
        }
    }

    @Test
    fun testLoadRatesFailure() {
        val repo = MockRepository()
        val api = MockApi()
        val activityAccess: IMainActivityAccess = mock(IMainActivityAccess::class.java)
        val viewModel = MainActivityViewModel(activityAccess, api, repo)

        api.success = false
        for (i in 1..10) {
            api.failureCallback = {
                assertEquals(repo.saveTablesCount, 0)
                verify(activityAccess, never()).displayRates(viewModel)
                verify(activityAccess, never()).displayRates(viewModel)
                verify(activityAccess, times(i)).hideProgressIndication()
            }

            viewModel.loadRates()
            verify(activityAccess, times(i)).showProgressIndication(false)
        }
    }
}