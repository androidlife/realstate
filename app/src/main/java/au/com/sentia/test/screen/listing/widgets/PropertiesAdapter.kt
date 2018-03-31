package au.com.sentia.test.screen.listing.widgets

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.sentia.test.R
import au.com.sentia.test.model.Property

class PropertiesAdapter(val listing: List<Property>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_NORMAL = 1
    private val TYPE_PREMIUM = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PremiumVH) {
            holder.bindPremium(position, getItemAt(position))
        } else if (holder is NormalVH) {
            holder.bindNormal(position, getItemAt(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PREMIUM ->
                PremiumVH(getViewFrom(parent, R.layout.list_item_premium))
            else ->
                NormalVH(getViewFrom(parent, R.layout.list_item_normal))

        }
    }

    private fun getViewFrom(parent: ViewGroup, layout: Int): View = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)

    override fun getItemCount(): Int {
        return listing.size
    }

    override fun getItemViewType(position: Int): Int {
        val property = getItemAt(position)
        if (property.isPremium)
            return TYPE_PREMIUM
        return TYPE_NORMAL
    }

    fun getItemAt(position: Int): Property {
        return listing[position]
    }

}
