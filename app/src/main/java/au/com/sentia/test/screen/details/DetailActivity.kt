package au.com.sentia.test.screen.details

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import au.com.sentia.test.R
import au.com.sentia.test.model.PROPERTY
import au.com.sentia.test.model.Property
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {


    companion object {
        fun getLaunchingIntent(context: Context, property: Property): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PROPERTY, property)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        if (intent.hasExtra(PROPERTY)) {
            val property: Property = intent.getParcelableExtra(PROPERTY) ?: return
            Glide.with(this).load(property.photo.imageLink.url)
                    .into(ivBackdrop)
            setSupportActionBar(toolBar)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivBackdrop.transitionName = property.title
            }
            supportActionBar?.title = property.title
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportFragmentManager.beginTransaction().replace(R.id.nsvContainer,
                    DetailFragment.getInstance(property)).commit()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}