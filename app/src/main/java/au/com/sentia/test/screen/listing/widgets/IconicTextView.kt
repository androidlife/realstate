package au.com.sentia.test.screen.listing.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import au.com.sentia.test.utils.Injection

class IconicTextView : TextView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        typeface = Injection.fontProvider.fontAwesome
    }

}
