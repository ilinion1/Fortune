package com.gerija.fortune.domain

class GetDataServerUseCase(private val repository: FortuneRepository) {

    operator fun invoke() = repository.getDataServer()

}