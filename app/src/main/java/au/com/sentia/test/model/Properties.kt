package au.com.sentia.test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Properties(@SerializedName("data") val listing: List<Property>) : Parcelable

@Parcelize
data class Property(@SerializedName("id") val id: Long,
                    @SerializedName("is_premium") val isPremium: Boolean,
                    @SerializedName("title") val title: String,
                    @SerializedName("bedrooms") val bedrooms: Int,
                    @SerializedName("bathrooms") val bathrooms: Int,
                    @SerializedName("carspots") val carspots: Int,
                    @SerializedName("description") val description: String,
                    @SerializedName("price") val price: Float,
                    @SerializedName("owner") val owner: Owner,
                    @SerializedName("location") val location: Location,
                    @SerializedName("photo") val photo: Photo) : Parcelable

@Parcelize
data class Owner(@SerializedName("first_name") val firstName: String,
                 @SerializedName("last_name") val lastName: String,
                 @SerializedName("avatar") val avatar: Avatar) : Parcelable

@Parcelize
data class Location(@SerializedName("address_1") val address1: String,
                    @SerializedName("address_2") val address2: String,
                    @SerializedName("suburb") val suburb: String,
                    @SerializedName("postcode") val postcode: String) : Parcelable

@Parcelize
data class Avatar(@SerializedName("medium") val avatarMedium: AvatarMedium) : Parcelable

@Parcelize
data class AvatarMedium(@SerializedName("url") val url: String) : Parcelable

@Parcelize
data class Photo(@SerializedName("image") val imageLink: ImageLink) : Parcelable

@Parcelize
data class ImageLink(@SerializedName("url") val url: String) : Parcelable