package au.com.sentia.test.screen.listing

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import au.com.sentia.test.R
import au.com.sentia.test.model.Property
import au.com.sentia.test.screen.details.DetailActivity
import au.com.sentia.test.screen.details.DetailFragment
import au.com.sentia.test.screen.listing.widgets.ListItemSpace
import au.com.sentia.test.screen.listing.widgets.PropertiesAdapter
import au.com.sentia.test.utils.Injection
import au.com.sentia.test.utils.events.EventClick
import au.com.sentia.test.utils.events.RxBus
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_listing.*
import kotlinx.android.synthetic.main.listing.*

class ListingActivity : AppCompatActivity(), ListContract.View {


    private var isTwoPane: Boolean = false
    private var viewState = ListContract.View.ViewState.Empty
    private lateinit var listAdapter: PropertiesAdapter
    private lateinit var detailFragment: DetailFragment
    private lateinit var clickListener: Disposable
    private lateinit var listing: List<Property>

    private lateinit var presenter: ListContract.Presenter

    private val VIEW_STATE: String = "viewState"
    private val SELECTED_INDEX: String = "selectedIndex"
    private val LIST: String = "list"
    private var selectedIndex: Int = 0


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

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
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
        val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    override fun setData(listing: List<Property>) {
        this.listing = listing
        listAdapter = PropertiesAdapter(listing)
        rvProperties.adapter = listAdapter
    }

    override fun getFetchedListing(): List<Property> {
        return listing
    }

    override fun showListingDetail(property: Property) =
            startActivity(DetailActivity.getLaunchingIntent(this, property))

    override fun showListingDetailInSamePane(property: Property) {
        if (detailFragment == null) {
            detailFragment = DetailFragment.getInstance(property)
            supportFragmentManager.beginTransaction().add(R.id.nsvContainer, detailFragment)
                    .commit()
        } else {
            detailFragment.setNewProperty(property)
        }
    }

    private fun onItemClick(event: Any) {
        if (event is EventClick) {
            selectedIndex = event.index
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
