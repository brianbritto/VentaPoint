package com.lucky.android.ventapoint.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "points")
class Point(
        @PrimaryKey//(autoGenerate = true)
        var id: Long = 0,

        var nombre: String = "",
        var latitud: Double = 0.0,
        var longitud: Double = 0.0
)