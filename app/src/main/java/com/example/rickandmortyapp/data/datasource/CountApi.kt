package com.example.rickandmortyapp.data.datasource

import com.example.rickandmortyapp.data.models.CountDto

class CountApi {
    private var count = 0
    private var typeOfOperation = "nothing"


    fun increment(){
        count++
        typeOfOperation = "increment"
    }
    fun decrement(){
        count--
        typeOfOperation = "decrement"
    }
    fun resetCount(){
        count = 0
        typeOfOperation = "reset"
    }
    fun getCount() : CountDto{
        return CountDto(
            count = count,
            typeOfOperation = typeOfOperation,
            createdAt = System.currentTimeMillis()
        )
    }
}