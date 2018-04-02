package au.com.sentia.test.screen.listing

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import au.com.sentia.test.R
import au.com.sentia.test.R.id.rvProperties
import au.com.sentia.test.model.Property
import au.com.sentia.test.screen.details.DetailActivity
import au.com.sentia.test.screen.details.DetailFragment
import au.com.sentia.test.screen.listing.widgets.ListItemSpace
import au.com.sentia.test.screen.listing.widgets.PropertiesAdapter
import au.com.sentia.test.utils.GeneralUtils
import au.com.sentia.test.utils.Injection
import au.com.sentia.test.screen.listing.widgets.EventClick
import au.com.sentia.test.utils.events.RxBus
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_listing.*
import kotlinx.android.synthetic.main.listing.*
import android.support.v4.view.ViewCompat
import android.support.v4.app.ActivityOptionsCompat


class ListingActivity : AppCompatActivity(), ListContract.View {


    private var isTwoPane: Boolean = false
    private var viewState = ListContract.View.ViewState.Empty
    private lateinit var listAdapter: PropertiesAdapter
    private var detailFragment: DetailFragment? = null
    private lateinit var clickListener: Disposable
    private var listing: List<Property> = ArrayList(0)

    private lateinit var presenter: ListContract.Presenter

    private val VIEW_STATE: String = "viewState"
    private val SELECTED_INDEX: String = "selectedIndex"
    private val LIST: String = "list"
    private var selectedIndex: Int = 0
    private lateinit var selectedImage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        initViews()

        presenter = ListPresenter(this, Injection.listModel)
        tvInfo.setOnClickListener({ _ -> presenter.retry() })
    }

    private fun initViews() {
        isTwoPane = detailContainer != null
        swipeRefLayout.isEnabled = false
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvProperties.layoutManager = linearLayoutManager
        val left = resources.getDimensionPixelSize(R.dimen.row_space_left)
        val right = resources.getDimensionPixelSize(R.dimen.row_space_right)
        val top = resources.getDimensionPixelSize(R.dimen.row_space_top)
        val bottom = resources.getDimensionPixelSize(R.dimen.row_space_bottom)
        val listItemSpace = ListItemSpace(left, top, right, bottom)
        rvProperties.addItemDecoration(listItemSpace)

    }

    override fun isShowingTwoPanes(): Boolean {
        return isTwoPane
    }

    override fun onPause() {
        super.onPause()
        listenToClick(false)
        presenter.start(false)
    }

    override fun onResume() {
        super.onResume()
        listenToClick(true)
        presenter.start(true)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(VIEW_STATE, viewState.name)
        outState?.putInt(SELECTED_INDEX, selectedIndex)
        outState?.putParcelableArrayList(LIST, ArrayList(listing))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(VIEW_STATE)) {
            viewState = ListContract.View.ViewState.valueOf(savedInstanceState.getString(VIEW_STATE))
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX)
            listing = savedInstanceState.getParcelableArrayList(LIST)
        }
    }

    override fun showProgress(show: Boolean) {
        swipeRefLayout.isRefreshing = show
    }

    override fun showError(show: Boolean) {
        when (show) {
            true -> tvInfo.visibility = android.view.View.VISIBLE
            else -> tvInfo.visibility = android.view.View.GONE
        }
    }

    override fun showInfo(infoMsg: String) {
        Snackbar.make(swipeRefLayout, infoMsg, Snackbar.LENGTH_SHORT).show()
    }

    override fun setViewState(viewViewState: ListContract.View.ViewState) {
        this.viewState = viewViewState
    }

    override fun getViewState(): ListContract.View.ViewState = viewState

    override fun isConnectedToNetwork(): Boolean {
        return GeneralUtils.isConnectedToNetwork(this)
    }

    override fun setData(listing: List<Property>) {
        this.listing = listing
        listAdapter = PropertiesAdapter(listing)
        rvProperties.adapter = listAdapter
    }

    override fun getFetchedListing(): List<Property> {
        return listing
    }

    override fun showListingDetail(property: Property) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                selectedImage,
                ViewCompat.getTransitionName(selectedImage))
        startActivity(DetailActivity.getLaunchingIntent(this, property), options.toBundle())

    }

    override fun showListingDetailInSamePane(property: Property) {
        if (detailFragment == null) {
            detailFragment = DetailFragment.getInstance(property)
            supportFragmentManager.beginTransaction().add(R.id.detailContainer, detailFragment)
                    .commit()
        } else {
            detailFragment?.setNewProperty(property)
        }
    }

    private fun onItemClick(event: Any) {
        if (event is EventClick) {
            selectedIndex = event.index
            selectedImage = event.iv
            presenter.onListingSelected(event.property)
        }
    }

    override fun scrollToIndex(index: Int) {
        rvProperties.scrollToPosition(index)
    }

    override fun getSelectedIndex(): Int {
        return selectedIndex
    }

    override fun getSelectedProperty(index: Int): Property {
        return listAdapter.getItemAt(index)
    }

    private fun listenToClick(listen: Boolean) {
        when (listen) {
            true ->
                clickListener = RxBus.toObservable().subscribe({ event -> onItemClick(event) })
            false ->
                if (clickListener != null)
                    clickListener.dispose()

        }

    }


}
