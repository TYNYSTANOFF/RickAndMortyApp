package com.example.rickandmortyapp.data.datasource

import com.example.rickandmortyapp.data.models.BaseResponseDto
import com.example.rickandmortyapp.domain.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CartoonApi {

    @GET("character")
    suspend fun getCharacters(
        @Query ("page") page : Int
    ): Response<BaseResponseDto>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int?
    ): Response<Character>
}