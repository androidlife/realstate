package au.com.sentia.test

import android.app.Application
import android.content.Context
import au.com.sentia.test.Injection.fontProvider
import au.com.sentia.test.utils.FontProvider

class MainApplication : Application() {;

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
