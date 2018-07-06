package com.lucky.android.ventapoint.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.lucky.android.ventapoint.db.repository.ProductRepository

class ProductViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository: ProductRepository

    init {
        mRepository = ProductRepository(application)
    }

    fun getAllProductsOfPoint(productId: Long) = mRepository.getAllProductsOfPoint(productId)

    fun updateStock(productId: Long, pointId:Long, newStock: Int) = mRepository.updateStock(productId, pointId, newStock)
}