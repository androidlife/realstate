package au.com.sentia.test.utils

import au.com.sentia.test.MainApplication
import au.com.sentia.test.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object Injection {
    val fontProvider: FontProvider = FontProvider(MainApplication.context.assets)

    val profileImageOptions: RequestOptions = RequestOptions()
            .override(ResProvider.getDimension(R.dimen.image_agent_resize),
                    ResProvider.getDimension(R.dimen.image_agent_resize))
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_person)
            .placeholder(R.drawable.ic_person)

    val imageOptions: RequestOptions = RequestOptions()
            .override(ResProvider.getDimension(R.dimen.image_resize_width),
                    ResProvider.getDimension(R.dimen.image_resize_height))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.placeholder)


}