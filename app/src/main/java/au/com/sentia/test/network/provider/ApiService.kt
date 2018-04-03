package au.com.sentia.test.network.provider

import au.com.sentia.test.model.Properties
import au.com.sentia.test.network.URL_PROP
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET(URL_PROP)
    fun getAllProperties(): Single<Properties>

}
