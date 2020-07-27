package com.leehendryp.wtest.core

import android.app.Application
import com.leehendryp.wtest.core.di.AppComponent
import com.leehendryp.wtest.core.di.DaggerAppComponent

class CustomApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}