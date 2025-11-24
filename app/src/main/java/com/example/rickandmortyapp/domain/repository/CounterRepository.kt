package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.domain.models.Count

interface CounterRepository {
    fun increment()

    fun decrement()

    fun getCount (): Count

    fun resetCount()
}