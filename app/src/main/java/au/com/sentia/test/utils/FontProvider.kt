package au.com.sentia.test.utils

import android.content.res.AssetManager
import android.graphics.Typeface

//creating singleton typeface to be used within whole application
class FontProvider(asset: AssetManager) {
    companion object {
        const val FONT_AWESOME: String = "fonts/awesome_solid.ttf"
    }

    val fontAwesome: Typeface = Typeface.createFromAsset(asset, FONT_AWESOME)
}
