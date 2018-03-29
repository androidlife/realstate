package au.com.sentia.test.network.provider

object ApiManager {
    val apiService: ApiService = RetrofitProvider.retrofit.create(ApiService::class.java)
}
