package au.com.sentia.test.screen

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import au.com.sentia.test.R
import au.com.sentia.test.screen.listing.ListingActivity
import au.com.sentia.test.screen.utils.CustomIdlingResource
import au.com.sentia.test.screen.utils.CustomViewActions.clickRecyclerItem
import au.com.sentia.test.screen.utils.CustomViewActions.getTotalItemsFromRecyclerAdapter
import au.com.sentia.test.screen.utils.CustomViewActions.recyclerScrollTo
import au.com.sentia.test.screen.utils.CustomViewMatchers.isSwipeRefreshing
import au.com.sentia.test.utils.GeneralUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ListActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<ListingActivity>(ListingActivity::class.java)

    private val TIMEOUT_ESPRESSO = 60
    private val TIMEOUT_FETCH: Long = 6
    private val TIMEOUT_UNIT = TimeUnit.SECONDS

    private var idlingResource: CustomIdlingResource? = null


    @Before
    fun setUp() {
        IdlingPolicies.setMasterPolicyTimeout(TIMEOUT_ESPRESSO.toLong(), TIMEOUT_UNIT)
    }

    @After
    fun onTestComplete() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource)
        }
    }

    @Test
    fun fetchTest() {
        if (GeneralUtils.isConnectedToNetwork(activityTestRule.activity)) {
            val totalItems = getTotalFetchedItems()
            if (totalItems > 0) {
                onView(withId(R.id.rvProperties)).perform(recyclerScrollTo(totalItems - 1))
            }
        }
    }

    private fun getTotalFetchedItems(): Int {
        if (GeneralUtils.isConnectedToNetwork(activityTestRule.activity)) {
            onView(withId(R.id.swipeRefLayout)).check(matches(isSwipeRefreshing()))
            idlingResource = CustomIdlingResource()
            idlingResource?.startCountdown(TIMEOUT_FETCH, TIMEOUT_UNIT)
            return getTotalItemsFromRecyclerAdapter(R.id.rvProperties)
        }
        return -1

    }

    @Test
    fun goToDetailTest() {
        val totalItems = getTotalFetchedItems();
        if (totalItems > 0) {
            onView(withId(R.id.rvProperties)).perform(RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                            clickRecyclerItem(R.id.ivProperty)))
            onView(withId(R.id.tvDetail)).check(matches(isDisplayed()))
        }
    }
}
