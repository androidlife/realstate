package au.com.sentia.test.utils.events

import au.com.sentia.test.model.Property


object EventClick {
    lateinit var property: Property
    var index: Int = -1
    fun onPropertyClicked(index: Int, property: Property): EventClick {
        this.property = property
        this.index = index
        return this
    }
}
