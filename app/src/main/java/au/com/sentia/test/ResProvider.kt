package au.com.sentia.test

object ResProvider {
    fun getStringFromRes(resId: Int): String {
        return MainApplication.context.getString(resId)
    }

    fun getDimension(dimenId: Int): Int {
        return MainApplication.context.resources.getDimensionPixelSize(dimenId)
    }
}