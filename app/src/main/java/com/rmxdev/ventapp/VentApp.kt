package com.rmxdev.ventapp

import android.app.Application
import com.rmxdev.ventapp.di.appModule
import com.rmxdev.ventapp.di.dataModule
import com.rmxdev.ventapp.di.domainModule
import com.rmxdev.ventapp.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VentApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@VentApp)
            modules(
                appModule,
                dataModule,
                domainModule,
                presenterModule
            )
        }
    }
}