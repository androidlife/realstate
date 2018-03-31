package au.com.sentia.test.screen.listing.widgets

import android.support.v7.widget.RecyclerView
import android.view.View
import au.com.sentia.test.utils.Injection
import au.com.sentia.test.R
import au.com.sentia.test.utils.ResProvider
import au.com.sentia.test.model.Property
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_normal.*
import kotlinx.android.synthetic.main.list_item_normal.view.*

class NormalVH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
    fun bindNormal(property: Property) {
        Glide.with(containerView.context)
                .setDefaultRequestOptions(Injection.imageOptions)
                .load(property.photo.imageLink.url)
                .into(ivProperty)
        Glide.with(containerView.context)
                .setDefaultRequestOptions(Injection.profileImageOptions)
                .load(property.owner.avatar.avatarMedium.url)
                .into(ivAgent)
        containerView.tvAgent.text = property.owner.firstName.plus(property.owner.lastName)
        containerView.tvProperty.text = property.price.toString()
                .plus("\n")
                .plus(property.location.address1)
                .plus("\n")
                .plus(property.location.address2)
        containerView.tvPropertyMisc.text = ResProvider.getStringFromRes(R.string.icon_bed)
                .plus(" ")
                .plus(property.bedrooms)
                .plus(" ")
                .plus(ResProvider.getStringFromRes(R.string.icon_bath))
                .plus(" ")
                .plus(property.bathrooms)
                .plus(" ")
                .plus(ResProvider.getStringFromRes(R.string.icon_car))
                .plus(" ")
                .plus(property.carspots)


    }

}
