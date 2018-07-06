package com.lucky.android.ventapoint.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.lucky.android.ventapoint.db.entity.Point
import com.lucky.android.ventapoint.db.repository.PointRepository

class PointViewModel(application: Application): AndroidViewModel(application) {

    private var mRepository: PointRepository? = null
    private var mAllPoints : LiveData<List<Point>>? = null

    init {
        mRepository = PointRepository(application)
        mAllPoints = mRepository?.getAllPoints()
    }

    fun getAllPoints(): LiveData<List<Point>>? = mAllPoints


}