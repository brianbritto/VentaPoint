package com.lucky.android.ventapoint.db.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.lucky.android.ventapoint.db.AppDatabase
import com.lucky.android.ventapoint.db.dao.PointDao
import com.lucky.android.ventapoint.db.entity.Point

class PointRepository(application: Application) {

    private var mPointDao : PointDao? = null
    private var mAllPoints : LiveData<List<Point>>? = null

    init {
        val db = AppDatabase.getDatabase(application)
        mPointDao = db?.pointDao()
        mAllPoints = mPointDao?.getAllPoints()

    }

    fun getAllPoints(): LiveData<List<Point>>? {
        return mAllPoints
    }
}