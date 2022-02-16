package com.gerija.fortune.data.repository

import com.gerija.fortune.data.RemoteConfigUtils
import com.gerija.fortune.domain.FortuneRepository

class FortuneRepositoryImpl: FortuneRepository {


    override fun getDataServer() {
        RemoteConfigUtils.init()
    }
}