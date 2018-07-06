package com.lucky.android.ventapoint.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

@Entity(tableName = "products_users",
        primaryKeys = ["product_id", "user_id"],
        foreignKeys = [(ForeignKey(entity = Product::class,
                                   parentColumns = ["id"],
                                   childColumns = ["product_id"])),
                       (ForeignKey(entity = User::class,
                                   parentColumns = ["id"],
                                   childColumns = ["user_id"]))])
class ProductUser(
        @ColumnInfo(name = "product_id")
        var productId: Long = 0,
        @ColumnInfo(name = "user_id")
        var userId: Long = 0,

        var cantidad: Int = 0
)