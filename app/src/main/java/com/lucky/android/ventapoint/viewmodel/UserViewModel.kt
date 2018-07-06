package com.lucky.android.ventapoint.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.lucky.android.ventapoint.db.entity.User
import com.lucky.android.ventapoint.db.repository.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository : UserRepository

    init {
        mRepository = UserRepository(application)
    }

    fun login(userName:String, password:String): User? = mRepository.login(userName, password)
}