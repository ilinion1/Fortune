package com.gerija.fortune.presentation

import android.app.Application
import com.gerija.fortune.data.RemoteConfigUtils

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        RemoteConfigUtils.init()
    }
}