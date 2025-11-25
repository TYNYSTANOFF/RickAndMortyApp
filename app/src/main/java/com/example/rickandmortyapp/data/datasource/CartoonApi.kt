package com.example.rickandmortyapp.data.datasource

import retrofit2.http.GET

interface CartoonApi {

    @GET("character")
    suspend fun getCharacters(): List<String>
}