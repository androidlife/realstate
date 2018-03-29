package au.com.sentia.test.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import au.com.sentia.test.Injection

class CustomFontTV : TextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        typeface = Injection.fontProvider.fontAwesome
    }

}
