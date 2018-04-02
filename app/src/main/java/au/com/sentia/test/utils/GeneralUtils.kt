package au.com.sentia.test.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object GeneralUtils {

    fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

}
