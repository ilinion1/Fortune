package com.gerija.fortune.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gerija.fortune.data.RemoteConfigUtils
import com.gerija.fortune.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var visited: SharedPreferences
    private lateinit var bot: SharedPreferences
    private val viewModel: GameViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getDataServerUseCase()
        getAnswerServer()
    }

    /**
     * Получаю ответ от сервера и запускаю webView или игру
     */
    private fun getAnswerServer() {
        if (firstVisit()|| RemoteConfigUtils.repeatFirstVisited()){

            //ожидаю ответ от сервера, если true, показываю webView, если false игру
            RemoteConfigUtils.status.observe(this){
                if (RemoteConfigUtils.getAnswerService()) {
                    //при первом входе задаю значение, куда определило пользователя
                    bot = getSharedPreferences(BOT, Context.MODE_PRIVATE)
                    bot.edit().putBoolean(BOT, false).apply()

                    startActivity(Intent(this, WebViewActivity::class.java))
                } else {
                    //при первом входе задаю значение, куда определило пользователя
                    bot = getSharedPreferences(BOT, Context.MODE_PRIVATE)
                    bot.edit().putBoolean(BOT, true).apply()

                    startActivity(Intent(this, GameActivity::class.java))
                }
            }
            visited.edit().putBoolean(VISIT, false).apply()
        } else {
            if (bot()){
                //при повторном входе, если определило к боту, запускаю игру
                startActivity(Intent(this, GameActivity::class.java))
            } else {

                //при повторном входе, если определило к пользователю, запускаю webView
                startActivity(Intent(this, WebViewActivity::class.java))
            }
        }

    }

    /**
     * Проверяю первый вход или нет
     * Если первый, получаю запрос с сервера, если нет, открываю что было при первом входе
     */
    private fun firstVisit(): Boolean {
        visited = getSharedPreferences(VISIT, Context.MODE_PRIVATE)
        return visited.getBoolean(VISIT, true)
    }

    /**
     * Проверяю бот или пользователь
     */
    private fun bot(): Boolean {
        bot = getSharedPreferences(BOT, Context.MODE_PRIVATE)
        return bot.getBoolean(BOT, true)
    }

    companion object{
        private const val VISIT = "visit"
        private const val BOT = "bot"
    }
}