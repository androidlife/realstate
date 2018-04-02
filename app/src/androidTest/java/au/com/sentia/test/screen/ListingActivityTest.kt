package au.com.sentia.test.screen

import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import au.com.sentia.test.R
import au.com.sentia.test.screen.listing.ListingActivity
import au.com.sentia.test.screen.utils.CustomIdlingResource
import au.com.sentia.test.screen.utils.CustomViewMatchers
import au.com.sentia.test.utils.GeneralUtils
import kotlinx.android.synthetic.main.listing.*
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class ListingActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<ListingActivity>(ListingActivity::class.java)

    private val TIMEOUT_ESPRESSO = 60
    private val TIMEOUT_FETCH = 6
    private val TIMEOUT_UNIT = TimeUnit.SECONDS

    private val idlingResource: CustomIdlingResource? = null

    @After
    fun onTestComplete() {
        if (idlingResource != null)
            IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun fetchTest() {
        if (GeneralUtils.isConnectedToNetwork(activityTestRule.activity)) {
            Espresso.onView(ViewMatchers.withId(R.id.swipeRefLayout)).check(ViewAssertions.matches(
                    CustomViewMatchers.isSwipeRefreshing()
            ))
        }
    }
}