package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.domain.repository.CounterRepository

class DecrementUseCase (
    private val repository: CounterRepository
) {
    fun decrement (){
        repository.decrement()
    }
}