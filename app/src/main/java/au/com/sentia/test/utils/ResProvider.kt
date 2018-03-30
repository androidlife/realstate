package au.com.sentia.test.utils

import au.com.sentia.test.MainApplication

object ResProvider {
    fun getStringFromRes(resId: Int): String {
        return MainApplication.context.getString(resId)
    }

    fun getDimension(dimenId: Int): Int {
        return MainApplication.context.resources.getDimensionPixelSize(dimenId)
    }
}