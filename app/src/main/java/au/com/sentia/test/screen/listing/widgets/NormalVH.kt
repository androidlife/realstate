package au.com.sentia.test.screen.listing.widgets

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.View
import au.com.sentia.test.utils.Injection
import au.com.sentia.test.R
import au.com.sentia.test.utils.ResProvider
import au.com.sentia.test.model.Property
import au.com.sentia.test.utils.events.EventClick
import au.com.sentia.test.utils.events.RxBus
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_normal.*
import kotlinx.android.synthetic.main.list_item_normal.view.*

class NormalVH(override val containerView: View) : ListVH(containerView) {

    val propertySSB = SpannableStringBuilder()

    fun bindNormal(index: Int, property: Property) {
        updateValues(index, property)
        Glide.with(containerView.context)
                .setDefaultRequestOptions(Injection.imageOptions)
                .load(property.photo.imageLink.url)
                .into(ivProperty)
        Glide.with(containerView.context)
                .setDefaultRequestOptions(Injection.profileImageOptions)
                .load(property.owner.avatar.avatarMedium.url)
                .into(ivAgent)
        containerView.tvAgent.text = property.owner.firstName
                .plus(" ")
                .plus(property.owner.lastName)
        createPropertySSB()
        containerView.tvProperty.text = propertySSB
        containerView.tvPropertyMisc.text = ResProvider.getStringFromRes(R.string.icon_bed)
                .plus(spaces)
                .plus(property.bedrooms)
                .plus(spaces)
                .plus(ResProvider.getStringFromRes(R.string.icon_bath))
                .plus(spaces)
                .plus(property.bathrooms)
                .plus(spaces)
                .plus(ResProvider.getStringFromRes(R.string.icon_car))
                .plus(spaces)
                .plus(property.carspots)
        containerView.ivProperty.setOnClickListener { v -> onViewClicked() }
    }

    private fun createPropertySSB() {
        propertySSB.clear()
        val price = "$ ".plus(property.price.toString()).plus("\n")
        var startIndex = 0
        var endIndex = price.length
        propertySSB.append(price)
        propertySSB.setSpan(priceTextAppearance, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        propertySSB.append(property.location.address1).append("\n")
        startIndex = endIndex
        endIndex += property.location.address1.length + 1
        propertySSB.setSpan(address2TextAppearance, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        propertySSB.append(property.location.address2)
        startIndex = endIndex
        endIndex += property.location.address2.length
        propertySSB.setSpan(address1TextAppearance, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

}
