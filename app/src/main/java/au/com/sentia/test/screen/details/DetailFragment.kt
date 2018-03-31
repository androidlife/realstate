package au.com.sentia.test.screen.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import au.com.sentia.test.R
import au.com.sentia.test.model.Extras
import au.com.sentia.test.model.Property
import kotlinx.android.synthetic.main.detail.*

class DetailFragment : Fragment() {
    lateinit var property: Property

    companion object {
        fun getInstance(property: Property): DetailFragment {
            val detailFragment = DetailFragment()
            val args = Bundle()
            args.putParcelable(Extras.PROPERTY, property)
            detailFragment.arguments = args
            return detailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater?.inflate(R.layout.detail, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvDetail.movementMethod = ScrollingMovementMethod()
        setNewProperty(arguments.getParcelable(Extras.PROPERTY))
    }

    fun setNewProperty(property: Property) {
        this.property = property
        tvDetail.text = this.property.title.plus("\n\n").plus(this.property.description)
    }


}

