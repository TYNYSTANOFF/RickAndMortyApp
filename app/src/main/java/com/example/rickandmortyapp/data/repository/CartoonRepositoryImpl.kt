package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.datasource.CartoonApi
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CartoonRepositoryImpl(
    private val api : CartoonApi
): CartoonRepository {

    override fun getCharacters(): Flow<List<String>> {
       return flow {
           try {
               val response = api.getCharacters()
               emit(response)
           }catch (e: Exception){

           }
       }.flowOn(Dispatchers.IO)
    }
}