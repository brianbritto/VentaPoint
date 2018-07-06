package com.lucky.android.ventapoint.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lucky.android.ventapoint.R
import com.lucky.android.ventapoint.db.entity.User
import com.lucky.android.ventapoint.util.DataCache
import com.lucky.android.ventapoint.util.navigate
import com.lucky.android.ventapoint.util.toast
import com.lucky.android.ventapoint.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //AppDatabase.getDatabase(this)

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        btnIngresar.setOnClickListener {
            val userName = edtUsuario.text.toString()
            val password = edtPassword.text.toString()
            if (userName!="" && password!=""){
                val user: User? = mUserViewModel.login(userName, password)
                if (user != null){
                    DataCache.userCurrent = user
                    navigate<PointsActivity>()
                    toast("Bienvenido ${user.nombre} ${user.apellido}")
                } else {
                    toast("Usuario y/o password incorrectos.")
                }


            }
        }
    }
}
