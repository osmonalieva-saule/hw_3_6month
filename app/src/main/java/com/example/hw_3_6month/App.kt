package com.example.hw_3_6month

import android.app.Application
import com.example.hw_3_6month.data.module.dataModule
import com.example.hw_3_6month.data.module.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule, roomModule
            )
        }
    }

}