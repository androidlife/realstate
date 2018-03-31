package au.com.sentia.test.screen.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import au.com.sentia.test.R
import au.com.sentia.test.model.Extras
import au.com.sentia.test.model.Property

class DetailActivity : AppCompatActivity() {

    lateinit private var property: Property

    companion object {
        fun getLaunchingIntent(context: Context, property: Property): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(Extras.PROPERTY, property)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        property = intent.getParcelableExtra(Extras.PROPERTY)
        supportFragmentManager.beginTransaction().replace(R.id.nsvContainer,
                DetailFragment.getInstance(property)).commit()
    }


}