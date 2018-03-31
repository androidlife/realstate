package au.com.sentia.test.utils

import android.text.style.TextAppearanceSpan

object ResProvider {
    fun getStringFromRes(resId: Int): String {
        return Injection.getContext().getString(resId)
    }

    fun getDimension(dimenId: Int): Int {
        return Injection.getContext().resources.getDimensionPixelSize(dimenId)
    }

    fun getTextAppearanceSpan(styleId: Int): TextAppearanceSpan {
        return TextAppearanceSpan(Injection.getContext(), styleId)
    }
}