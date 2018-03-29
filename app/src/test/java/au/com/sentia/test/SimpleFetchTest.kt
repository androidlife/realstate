package au.com.sentia.test

import au.com.sentia.test.model.Properties
import au.com.sentia.test.model.Property
import au.com.sentia.test.network.provider.ApiManager
import org.junit.Test

import org.junit.Assert.*

class SimpleFetchTest {
    @Test
    fun propertiesFetchTest() {
        val properties: Properties = ApiManager.apiService.getAllProperties().blockingFirst()
        assertTrue(properties != null)
        assertTrue(properties.listing.isNotEmpty())
        val property:Property = properties.listing[0]
        assertTrue(property.owner.firstName.isNotEmpty())
        assertTrue(property.photo.imageLink.url.isNotEmpty())
        assertTrue(property.location.address1.isNotEmpty() &&
        property.location.address2.isNotEmpty())
        assertTrue(property.owner.avatar.avatarMedium.url.isNotEmpty())
    }
}
