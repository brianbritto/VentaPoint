package com.lucky.android.ventapoint.db.repository

import android.app.Application
import com.lucky.android.ventapoint.db.AppDatabase
import com.lucky.android.ventapoint.db.dao.UserDao
import com.lucky.android.ventapoint.db.entity.User

class UserRepository(application: Application) {

    private val mUserDao : UserDao

    init {
        val db = AppDatabase.getDatabase(application)!!
        mUserDao = db.userDao()
    }

    fun login(userName:String, password:String): User? = mUserDao.login(userName, password)
}