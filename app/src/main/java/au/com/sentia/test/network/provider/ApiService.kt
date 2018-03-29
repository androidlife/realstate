package au.com.sentia.test.network.provider

import au.com.sentia.test.model.Properties
import au.com.sentia.test.network.Api
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET(Api.URL_PROP)
    fun getAllProperties(): Observable<Properties>

}
