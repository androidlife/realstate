package au.com.sentia.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import au.com.sentia.test.network.provider.ApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiManager.apiService.getAllProperties().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ prop -> println(prop.listing.size) }, {e->println()})

    }
}
