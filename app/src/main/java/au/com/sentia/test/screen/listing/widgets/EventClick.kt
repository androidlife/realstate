package au.com.sentia.test.screen.listing.widgets

import android.widget.ImageView
import au.com.sentia.test.model.Property


data class EventClick(val index: Int, val property: Property, val iv: ImageView)
