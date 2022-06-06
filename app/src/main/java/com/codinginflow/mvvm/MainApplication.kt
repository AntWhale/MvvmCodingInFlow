package com.codinginflow.mvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    private val configPath: String = "config.json"

    override fun onCreate() {
        super.onCreate()
        Config.init(this, configPath)
    }
}