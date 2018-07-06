package com.lucky.android.ventapoint.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
class Product(
        @PrimaryKey//(autoGenerate = true)
        var id: Long = 0,

        var nombre: String = "",
        var precio: Double = 0.0,
        //@Ignore
        var stock: Int = 0
)