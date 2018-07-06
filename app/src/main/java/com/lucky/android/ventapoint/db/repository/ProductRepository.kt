package com.lucky.android.ventapoint.db.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.lucky.android.ventapoint.db.AppDatabase
import com.lucky.android.ventapoint.db.dao.ProductDao
import com.lucky.android.ventapoint.db.entity.Product
import com.lucky.android.ventapoint.db.entity.ProductPoint


class ProductRepository(application: Application) {

    private val mProductDao : ProductDao

    init {
        val db = AppDatabase.getDatabase(application)!!
        mProductDao = db.productDao()
    }

    fun getAllProductsOfPoint(id:Long): LiveData<List<Product>> {
        return mProductDao.getAllProductsOfPoint(id)
    }

    fun updateStock(productId: Long, pointId:Long, newStock: Int){
        val runnable = Runnable { mProductDao.updateStock(productId, pointId, newStock) }
        runnable.run()
    }

    fun insertStock(productPoint: ProductPoint){
        mProductDao.insertStock(productPoint)
    }
}