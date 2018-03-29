package au.com.sentia.test

import au.com.sentia.test.utils.FontProvider

object Injection {
    val fontProvider:FontProvider = FontProvider(MainApplication.context.assets)
}