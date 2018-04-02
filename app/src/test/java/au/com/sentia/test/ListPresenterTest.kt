package au.com.sentia.test

import au.com.sentia.test.model.Error
import au.com.sentia.test.model.Properties
import au.com.sentia.test.model.Property
import au.com.sentia.test.screen.listing.ListContract
import au.com.sentia.test.screen.listing.ListPresenter
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.hamcrest.core.AnyOf
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when

class ListPresenterTest {
    @Mock
    lateinit var listModel: ListContract.Model
    @Mock
    lateinit var view: ListContract.View

    lateinit var listPresenter: ListContract.Presenter

    @Before
    fun initalize() {
        MockitoAnnotations.initMocks(this)
        listPresenter = ListPresenter(view, listModel)
    }

    @Test
    fun fetchSuccessTest() {
        val propertyFirst = Mockito.mock(Property::class.java)
        val propertySecond = Mockito.mock(Property::class.java)
        val list: List<Property> = listOf(propertyFirst, propertySecond)
        val properties = Mockito.mock(Properties::class.java)
        _when(properties.listing).thenReturn(list)
        _when(view.isConnectedToNetwork()).thenReturn(true)
        _when(view.getViewState()).thenReturn(ListContract.View.ViewState.Empty)

        listPresenter.start(true)

        argumentCaptor<ListContract.OnPropertiesFetchListener>().apply {
            Mockito.verify(listModel).fetchProperties(capture())
            firstValue.onFetchSuccess(properties)
        }
        Mockito.verify(view, times(2)).showProgress(ArgumentMatchers.anyBoolean())
        Mockito.verify(view).setData(list)
        Mockito.verify(view).setViewState(ListContract.View.ViewState.Loaded)
    }

    @Test
    fun verifyNetworkFailureTest() {
        _when(view.isConnectedToNetwork()).thenReturn(false)
        _when(view.getViewState()).thenReturn(ListContract.View.ViewState.Empty)
        listPresenter.start(true)
        Mockito.verify(view).showInfo(ArgumentMatchers.anyString())
        Mockito.verify(view).setViewState(ListContract.View.ViewState.Error)
    }

    @Test
    fun fetchFailureTest() {
        _when(view.isConnectedToNetwork()).thenReturn(true)
        _when(view.getViewState()).thenReturn(ListContract.View.ViewState.Empty)

        listPresenter.start(true)

        argumentCaptor<ListContract.OnPropertiesFetchListener>().apply {
            Mockito.verify(listModel).fetchProperties(capture())
            firstValue.onFetchFailure(au.com.sentia.test.model.Error(Error.Type.Fetch))
        }
        Mockito.verify(view).showInfo(ArgumentMatchers.anyString())
        Mockito.verify(view).setViewState(ListContract.View.ViewState.Error)
    }

    @Test
    fun retrySuccessTest() {
        val propertyFirst = Mockito.mock(Property::class.java)
        val list: List<Property> = listOf(propertyFirst)
        val properties = Mockito.mock(Properties::class.java)
        _when(properties.listing).thenReturn(list)
        _when(view.isConnectedToNetwork()).thenReturn(true)
        _when(view.getViewState()).thenReturn(ListContract.View.ViewState.Empty)

        listPresenter.retry()
        argumentCaptor<ListContract.OnPropertiesFetchListener>().apply {
            Mockito.verify(listModel).fetchProperties(capture())
            firstValue.onFetchSuccess(properties)
        }
        Mockito.verify(view).setData(list)
        Mockito.verify(view).setViewState(ListContract.View.ViewState.Loaded)
    }

    @Test
    fun `show detail in same pane`() {
        _when(view.isShowingTwoPanes()).thenReturn(true)
        val property = Mockito.mock(Property::class.java)
        listPresenter.onListingSelected(property)
        Mockito.verify(view).showListingDetailInSamePane(property)

    }
}
