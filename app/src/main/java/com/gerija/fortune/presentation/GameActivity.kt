package com.gerija.fortune.presentation


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gerija.fortune.R
import com.gerija.fortune.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding
    val viewModel: GameViewModel by viewModels()

    /**
     * Список всех картинок, которые используются в рандоме
     */
    private val imageList = arrayListOf(
        R.drawable.chaynik, R.drawable.fen, R.drawable.noutbuk, R.drawable.powerbank,
        R.drawable.pristavka, R.drawable.proig1, R.drawable.proig2, R.drawable.proig3,
        R.drawable.proig4, R.drawable.pulesos, R.drawable.samokat, R.drawable.telefon,
        R.drawable.utug, R.drawable.proig5, R.drawable.proig6, R.drawable.proig7,
        R.drawable.proig8,
    )

    /**
     * Список картинок с призами
     */
    private val winList = listOf(
        R.drawable.chaynik, R.drawable.fen, R.drawable.noutbuk,
        R.drawable.powerbank, R.drawable.pristavka, R.drawable.samokat, R.drawable.telefon,
        R.drawable.utug, R.drawable.pulesos
    )

    private var count = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.countWin.observe(this){
            binding.tvCount.text = it
            count = it.toInt()
        }
        viewModel.gameImage.observe(this){
            binding.imMain.setImageResource(it)
        }
        randomImage()
    }

    /**
     * рандом картинок
     */
    private fun randomImage() {
        binding.bPlay.setOnClickListener {
            val imageSize = imageList.size - 1
            val random = (0..imageSize).random()
            binding.imMain.setImageResource(imageList[random])
            viewModel.gameImage.value = imageList[random]
            countWin(imageList[random])
        }
    }

    /**
     * Задаю счет побед в игре
     */
    private fun countWin(image: Int) {
        winList.forEach {
            if (image == it) {
                count++
                binding.tvCount.text = count.toString()
                viewModel.countWin.value = count.toString()
            }
        }
    }
}