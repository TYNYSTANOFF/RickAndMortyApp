package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repository.CounterRepository

class ResetCountUseCase (
    private val repository: CounterRepository
) {
    fun resetCount (){
        repository.resetCount()
    }
}