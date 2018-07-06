package com.lucky.android.ventapoint

import android.app.Application
import com.facebook.stetho.Stetho
import com.lucky.android.ventapoint.db.AppDatabase

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.getDatabase(this)
        Stetho.initializeWithDefaults(this);
    }
}