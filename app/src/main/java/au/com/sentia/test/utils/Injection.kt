package au.com.sentia.test.utils

import android.content.Context
import au.com.sentia.test.MainApplication
import au.com.sentia.test.R
import au.com.sentia.test.network.provider.ApiManager
import au.com.sentia.test.screen.listing.ListContract
import au.com.sentia.test.screen.listing.ListModel
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

//this later can be replaced with DI library like Dagger2
object Injection {
    val fontProvider: FontProvider = FontProvider(getContext().assets)

    val profileImageOptions: RequestOptions = RequestOptions()
            .override(ResProvider.getDimension(R.dimen.image_agent_resize), ResProvider.getDimension(R.dimen.image_agent_resize))
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_person)
            .placeholder(R.drawable.ic_person)

    val imageOptions: RequestOptions = RequestOptions()
            .override(ResProvider.getDimension(R.dimen.image_resize_width), ResProvider.getDimension(R.dimen.image_resize_height))
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.placeholder)

    val listModel: ListContract.Model = ListModel(ApiManager.apiService)

    fun getContext(): Context {
        return MainApplication.context
    }


}