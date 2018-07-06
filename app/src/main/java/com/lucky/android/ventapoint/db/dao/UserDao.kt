package com.lucky.android.ventapoint.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.lucky.android.ventapoint.db.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("SELECT * from users WHERE userName = :userName AND password = :password")
    fun login(userName: String, password: String): User?
}