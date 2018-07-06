package com.lucky.android.ventapoint.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.lucky.android.ventapoint.db.entity.Point

@Dao
interface PointDao {

    @Insert
    fun insert(point: Point)

    @Query("SELECT * from points")
    fun getAllPoints(): LiveData<List<Point>>
}