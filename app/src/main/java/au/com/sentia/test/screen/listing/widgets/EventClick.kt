package au.com.sentia.test.screen.listing.widgets

import android.widget.ImageView
import au.com.sentia.test.model.Property


object EventClick {
    lateinit var property: Property
    var index: Int = -1
    lateinit var iv: ImageView
    fun onPropertyClicked(index: Int, property: Property, iv: ImageView): EventClick {
        EventClick.property = property
        EventClick.index = index
        EventClick.iv = iv
        return this
    }
}
