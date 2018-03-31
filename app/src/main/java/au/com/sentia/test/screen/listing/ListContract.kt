package au.com.sentia.test.screen.listing

import au.com.sentia.test.base.CancelCallback
import au.com.sentia.test.model.Error
import au.com.sentia.test.model.Properties
import au.com.sentia.test.model.Property

interface ListContract {

    interface View {
        enum class ViewState {
            Empty, Error, Loaded
        }


        fun getViewState(): ViewState
        fun setViewState(viewViewState: ViewState)
        fun showError(show: Boolean)
        fun showProgress(show: Boolean)
        fun showInfo(infoMsg: String)
        fun isConnectedToNetwork(): Boolean
        fun setData(listing: List<Property>)
        fun showListingDetail(property: Property)
        fun showListingDetailInSamePane(property: Property)
        fun scrollToIndex(index: Int)
        fun getSelectedIndex(): Int
        fun isShowingTwoPanes(): Boolean
        fun getSelectedProperty(index: Int): Property
    }

    interface Presenter {
        fun retry()
        fun onListingSelected(property: Property)
        fun start(start: Boolean)
    }

    interface Model : CancelCallback {
        fun fetchProperties(listener: OnPropertiesFetchListener)
    }

    interface OnPropertiesFetchListener {
        fun onFetchSuccess(properties: Properties)
        fun onFetchFailure(error: Error)
    }
}
