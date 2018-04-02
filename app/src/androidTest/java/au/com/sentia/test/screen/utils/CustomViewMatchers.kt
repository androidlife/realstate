package au.com.sentia.test.screen.utils

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CustomViewMatchers {

    fun isSwipeRefreshing(): Matcher<View> = IsSwipeRefereshingMatcher()
    private class IsSwipeRefereshingMatcher : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("Checking whether the swipe refresh layout is refreshing")
        }

        override fun matchesSafely(item: View?): Boolean {
            if (item is SwipeRefreshLayout) {
                return item?.visibility == View.VISIBLE
            }
            return false
        }

    }
}