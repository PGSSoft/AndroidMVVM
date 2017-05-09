
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.pgssoft.kotlinplayground.R
import com.pgssoft.kotlinplayground.model.db.Rate
import com.pgssoft.kotlinplayground.service.mock.MockRepository
import com.pgssoft.kotlinplayground.view.activity.MainActivity
import com.pgssoft.kotlinplayground.view.activity.RateActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by bstokrocki on 08-May-17.
 */
@RunWith(AndroidJUnit4::class)
class UITests {
    @get:Rule
    val rateActivityRule = ActivityTestRule(RateActivity::class.java, true, false)

    @Test
    fun testRateText() {
        val repo = MockRepository()

        fun innerTestRateText(rate: Rate) {
            val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
            val intent = Intent(targetContext, MainActivity::class.java)
            intent.putExtra(RateActivity.EXTRA_RATE_ID, rate.currencyCode)

            rateActivityRule.launchActivity(intent)

            onView(withId(R.id.rate)).check(matches(withText(rate.rate.toString())))

            rateActivityRule.activity.finish()
        }

        repo.rates.forEach { innerTestRateText(it) }
    }
}