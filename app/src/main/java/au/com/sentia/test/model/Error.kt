package au.com.sentia.test.model

import kotlin.Error

class Error(val type:Type) : Error() {
    enum class Type {
        Fetch,
        Network
    }
}
