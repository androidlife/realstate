package au.com.sentia.test.network.provider

import okhttp3.logging.HttpLoggingInterceptor

object Interceptors {
    fun getLoggingInterceptor(level: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = level
        return loggingInterceptor
    }
}
