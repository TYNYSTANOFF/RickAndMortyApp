package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repository.CounterRepository

class IncrementUseCase(
    private val repository: CounterRepository
) {
    fun increment (){
        repository.increment()
    }
}