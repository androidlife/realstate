package au.com.sentia.test.screen.listing.widgets

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import au.com.sentia.test.R
import au.com.sentia.test.model.Property
import au.com.sentia.test.utils.ResProvider
import au.com.sentia.test.utils.events.RxBus
import kotlinx.android.extensions.LayoutContainer

abstract class ListVH(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
    lateinit var property: Property
    var index: Int = -1
    val spaces = "   "
    val priceTextAppearance = ResProvider.getTextAppearanceSpan(R.style.PriceTextStyle)
    val address1TextAppearance = ResProvider.getTextAppearanceSpan(R.style.LocationTextStyle)
    val address2TextAppearance = ResProvider.getTextAppearanceSpan(R.style.LocationTextStyle)


    fun updateValues(index: Int, property: Property) {
        this.index = index
        this.property = property
    }

    fun onViewClicked(iv: ImageView) {
        RxBus.send(EventClick(index, property, iv))
    }


}
