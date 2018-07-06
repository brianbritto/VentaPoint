package com.lucky.android.ventapoint.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index

@Entity(tableName = "products_points",
        primaryKeys = ["product_id", "point_id"],
        foreignKeys = [(ForeignKey( entity = Product::class,
                                    parentColumns = ["id"],
                                    childColumns = ["product_id"],
                                    onDelete=CASCADE)),
                       (ForeignKey( entity = Point::class,
                                    parentColumns = ["id"],
                                    childColumns = ["point_id"],
                                    onDelete=CASCADE))],
        indices=[
            Index(value = ["product_id"]),
            Index(value = ["point_id"])
        ])
class ProductPoint(
    @ColumnInfo(name = "product_id")
    var productId: Long = 0,
    @ColumnInfo(name = "point_id")
    var pointId: Long = 0,

    var stock: Int = 0
)