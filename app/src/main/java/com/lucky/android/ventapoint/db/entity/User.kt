package com.lucky.android.ventapoint.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class User(
        @PrimaryKey//(autoGenerate = true)
        var id: Long = 0,

        var userName: String = "",
        var password: String = "",
        var nombre: String = "",
        var apellido: String = ""
)