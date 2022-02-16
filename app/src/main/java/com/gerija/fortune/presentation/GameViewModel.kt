package com.gerija.fortune.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gerija.fortune.data.repository.FortuneRepositoryImpl
import com.gerija.fortune.domain.GetDataServerUseCase


class GameViewModel : ViewModel() {

    val gameImage = MutableLiveData<Int>()
    val countWin = MutableLiveData<String>()
    private val repository = FortuneRepositoryImpl()
    val getDataServerUseCase = GetDataServerUseCase(repository)

}