package au.com.sentia.test.screen.listing.widgets

import android.view.View
import au.com.sentia.test.utils.Injection
import au.com.sentia.test.R
import au.com.sentia.test.utils.ResProvider
import au.com.sentia.test.model.Property
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_premium.*
import kotlinx.android.synthetic.main.list_item_premium.view.*

class PremiumVH(override val containerView: View) : ListVH(containerView) {

    fun bindPremium(index: Int, property: Property) {
        updateValues(index, property)
        containerView.tvAgent.text = property.owner.firstName
                .plus(" ")
                .plus(property.owner.lastName)
        containerView.tvPrice.text = "$ ".plus(property.price.toString())
        containerView.tvLocation.text = property.location.address1
                .plus("\n")
                .plus(property.location.address2)
        Glide.with(containerView.context)
                .setDefaultRequestOptions(Injection.profileImageOptions)
                .load(property.owner.avatar.avatarMedium.url)
                .into(ivAgent)
        Glide.with(containerView.context)
                .setDefaultRequestOptions(Injection.imageOptions)
                .load(property.photo.imageLink.url)
                .into(ivProperty)
        containerView.tvProperty.text = ResProvider.getStringFromRes(R.string.icon_bed)
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
        val star: String = ResProvider.getStringFromRes(R.string.icon_star)
        containerView.tvStar.text = star
                .plus(" ")
                .plus(star)
                .plus(" ")
                .plus(star)

        containerView.ivProperty.setOnClickListener({ v -> onViewClicked() })
    }
}
