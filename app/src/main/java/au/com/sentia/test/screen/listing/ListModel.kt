package au.com.sentia.test.screen.listing

import au.com.sentia.test.model.Error
import au.com.sentia.test.model.Properties
import au.com.sentia.test.network.provider.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListModel(private val apiService: ApiService) : ListContract.Model {
    private var cancel: Boolean = false
    override fun cancel(cancel: Boolean) {
        this.cancel = cancel
    }

    override fun fetchProperties(listener: ListContract.OnPropertiesFetchListener) {
        apiService.getAllProperties()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ properties -> onSuccess(properties, listener) },
                        { e ->
                            onFailure(au.com.sentia.test.model.Error(Error.Type.Fetch),
                                    listener)
                        })
    }

    private fun onSuccess(properties: Properties, listener: ListContract.OnPropertiesFetchListener) {
        if (cancel)
            return
        listener.onFetchSuccess(properties)
    }

    private fun onFailure(error: Error, listener: ListContract.OnPropertiesFetchListener) {
        if (cancel)
            return
        listener.onFetchFailure(error)
    }

}
