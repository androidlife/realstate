package au.com.sentia.test.screen.listing.widgets

import android.support.v7.widget.RecyclerView
import android.view.View
import au.com.sentia.test.model.Property
import au.com.sentia.test.utils.events.EventClick
import au.com.sentia.test.utils.events.RxBus
import kotlinx.android.extensions.LayoutContainer

abstract class ListVH(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
    lateinit var property: Property
    var index: Int = -1

    fun updateValues(index: Int, property: Property) {
        this.index = index
        this.property = property
    }

    fun addClickListener() {
        containerView.setOnClickListener({ v -> RxBus.send(EventClick.onPropertyClicked(index, property)) })
    }

}
