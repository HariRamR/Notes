package com.hari.notes.presentation.base

import android.app.Application
import com.hari.notes.di.DIModules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
            androidContext(applicationContext)
        }
    }
}