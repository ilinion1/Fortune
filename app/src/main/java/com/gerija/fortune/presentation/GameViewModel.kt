package com.gerija.fortune.presentation



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class GameViewModel: ViewModel() {

    val gameImage = MutableLiveData<Int>()
    val countWin = MutableLiveData<String>()

}