package au.com.sentia.test.widgets

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import au.com.sentia.test.R
import au.com.sentia.test.model.Property

class PropertiesAdapter(val listing: List<Property>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_NORMAL = 1
    private val TYPE_PREMIUM = 2

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PremiumVH) {
            holder.bindPremium(getItem(position))
        } else if (holder is NormalVH) {
            holder.bindNormal(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PREMIUM -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.listing_premium, parent, false)
                PremiumVH(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.listing_normal, parent, false)
                NormalVH(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return listing.size
    }

    override fun getItemViewType(position: Int): Int {
        val property = getItem(position)
        if (property.isPremium)
            return TYPE_PREMIUM
        return TYPE_NORMAL
    }

    private fun getItem(position: Int): Property {
        return listing[position]
    }

}
