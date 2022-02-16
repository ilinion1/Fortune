package com.gerija.fortune.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.gerija.fortune.domain.FortuneRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class FortuneRepositoryImpl: FortuneRepository {

    companion object{
        private const val ANSWER_SERVICE = "answer"
        private const val REPEAT_FIRST_VISITED = "repeat_first_visited"
    }

    private val defaults: Map<String, Any> = hashMapOf(ANSWER_SERVICE to false)
    val status = MutableLiveData<Boolean>()



    @SuppressLint("StaticFieldLeak")
    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun getRemoteConfig(): FirebaseRemoteConfig {

    }
}