package au.com.sentia.test.screen.listing

import au.com.sentia.test.model.Error
import au.com.sentia.test.model.Properties
import au.com.sentia.test.model.Property
import io.reactivex.disposables.Disposable

class ListPresenter(val view: ListContract.View, val model: ListContract.Model) : ListContract.Presenter {

    override fun start(start: Boolean) {
        when (start) {
            true -> initView(view.getViewState())
            false -> model.cancel(true)
        }
    }

    private fun initView(viewState: ListContract.View.ViewState) {
        when (viewState) {
            ListContract.View.ViewState.Empty -> fetchListing()
            ListContract.View.ViewState.Error -> view.showError(true)
            ListContract.View.ViewState.Loaded -> {
                val selectedIndex = view.getSelectedIndex()
                view.scrollToIndex(selectedIndex)
                if (view.isShowingTwoPanes())
                    onListingSelected(view.getSelectedProperty(selectedIndex))
            }
        }
    }

    override fun retry() {
        view.setViewState(ListContract.View.ViewState.Empty)
        fetchListing()
    }

    private fun fetchListing() {
        if (view.getViewState() == ListContract.View.ViewState.Empty) {
            if (!view.isConnectedToNetwork()) {
                setViewError("No internet connection")
                return
            }
            model.cancel(false)
            view.showError(false)
            view.showProgress(true)
            model.fetchProperties(object : ListContract.OnPropertiesFetchListener {
                override fun onFetchSuccess(properties: Properties) {
                    view.showProgress(false)
                    if (properties.listing.isEmpty())
                        view.showInfo("No properties found")
                    else {
                        view.setData(properties.listing)
                        view.setViewState(ListContract.View.ViewState.Loaded)
                    }
                }

                override fun onFetchFailure(error: Error) {
                    setViewError("Error fetching properties")
                }
            })
        }
    }

    override fun onListingSelected(property: Property) {
        when (view.isShowingTwoPanes()) {
            true -> view.showListingDetailInSamePane(property)
            false -> view.showListingDetail(property)
        }
    }


    private fun setViewError(errorMsg: String) {
        view.showProgress(false)
        view.setViewState(ListContract.View.ViewState.Error)
        view.showError(true)
        view.showInfo(errorMsg)
    }


}