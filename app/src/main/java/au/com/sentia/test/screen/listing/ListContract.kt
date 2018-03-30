package au.com.sentia.test.screen.listing

import au.com.sentia.test.base.CancelCallback
import au.com.sentia.test.model.Error
import au.com.sentia.test.model.Properties

interface ListContract {
    interface Model : CancelCallback {
        fun fetchProperties(listener: OnPropertiesFetchListener)
    }

    interface OnPropertiesFetchListener {
        fun onFetchSuccess(properties: Properties)
        fun onFetchFailure(error: Error)
    }
}
