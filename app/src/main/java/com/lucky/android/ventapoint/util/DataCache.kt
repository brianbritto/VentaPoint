package com.lucky.android.ventapoint.util

import com.lucky.android.ventapoint.db.entity.Point
import com.lucky.android.ventapoint.db.entity.Product
import com.lucky.android.ventapoint.db.entity.User

object DataCache {
    var productCurrent: Product? = null
    var pointCurrent: Point? = null
    var userCurrent: User? = null
}