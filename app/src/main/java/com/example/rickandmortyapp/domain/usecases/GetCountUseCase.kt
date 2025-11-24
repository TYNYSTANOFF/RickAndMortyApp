package com.example.counterca.domain.usecases

import com.example.rickandmortyapp.domain.models.Count
import com.example.rickandmortyapp.domain.repository.CounterRepository

class GetCountUseCase (
    private val repository: CounterRepository
) {
    fun getCount (): Count{
        return repository.getCount()
    }
}