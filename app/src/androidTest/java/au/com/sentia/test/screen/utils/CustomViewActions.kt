package au.com.sentia.test.screen.utils

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object CustomViewActions {
    fun getTotalItemsFromRecyclerAdapter(resId: Int): Int {
        val counts = IntArray(1) { -1 }
        onView(withId(resId)).perform(object : ViewAction {
            override fun getDescription(): String {
                return "Getting recyclerview adapter's item count"
            }

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(RecyclerView::class.java)
            }

            override fun perform(uiController: UiController?, view: View?) {
                if (view is RecyclerView) {
                    val count = view?.adapter?.itemCount
                    if (count != null)
                        counts[0] = count
                }
            }

        })
        return counts[0]
    }

    fun recyclerScrollTo(position: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                //just checking whether thew view is recyclerview and is displayed
                return allOf<View>(isAssignableFrom(RecyclerView::class.java), isDisplayed())
            }

            override fun getDescription(): String {
                return "recyclerScrollto:"
            }

            override fun perform(uiController: UiController, view: View) {
                val recyclerView = view as RecyclerView
                recyclerView?.scrollToPosition(position)
            }
        }
    }

    fun clickRecyclerItem( withId: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription(): String {
                return "Performing click on recycler view item "
            }

            override fun getConstraints(): Matcher<View> {
                return allOf<View>(isAssignableFrom(View::class.java), isDisplayed())

            }

            override fun perform(uiController: UiController?, view: View?) {
                view?.findViewById<View>(withId)?.performClick()

            }

        }
    }
}
