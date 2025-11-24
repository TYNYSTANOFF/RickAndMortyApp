package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.datasource.CountApi
import com.example.rickandmortyapp.data.mapper.mapToDomain
import com.example.rickandmortyapp.domain.models.Count
import com.example.rickandmortyapp.domain.repository.CounterRepository

class CounterRepositoryImpl(
    private val api: CountApi,

) : CounterRepository {
    override fun increment() {
        api.increment()
    }

    override fun decrement() {
        api.decrement()
    }

    override fun getCount(): Count {
        return api.getCount().mapToDomain()
    }


    override fun resetCount() {
        api.resetCount()
    }


}