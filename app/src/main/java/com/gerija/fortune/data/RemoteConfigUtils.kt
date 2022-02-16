package com.gerija.fortune.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigUtils {

    //для ответа с сервера бот или пользователь
    private const val ANSWER_SERVICE = "answer"

    //для ответа с сервера, на случай через него задать повторный "первый вход"
    private const val REPEAT_FIRST_VISITED = "repeat_first_visited"

    private val defaults: Map<String, Any> = hashMapOf(ANSWER_SERVICE to false)

    //ответ от сервера, true или false
    val status = MutableLiveData<Boolean>()



    @SuppressLint("StaticFieldLeak")
    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }


    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
        }

        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(defaults)
            fetchAndActivate().addOnCompleteListener {
                status.value = true
            }

        }

        return remoteConfig
    }

    /**
     * Получаю ответ от сервера, true или false
     */
    fun getAnswerService() = remoteConfig.getBoolean(ANSWER_SERVICE)

    /**
     * Если через сервер задам значение true, то будет повторно запущана процедура "первый вход"
     */
    fun repeatFirstVisited() = remoteConfig.getBoolean(REPEAT_FIRST_VISITED)
}