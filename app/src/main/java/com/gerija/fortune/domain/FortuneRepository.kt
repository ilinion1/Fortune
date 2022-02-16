package com.gerija.fortune.domain

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

interface FortuneRepository {

    fun getRemoteConfig(): FirebaseRemoteConfig
}