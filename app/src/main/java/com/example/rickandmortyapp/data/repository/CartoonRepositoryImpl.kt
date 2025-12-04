package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.data.base.BaseRepository
import com.example.rickandmortyapp.data.datasource.CartoonApi
import com.example.rickandmortyapp.data.mapper.toDomain
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow

class CartoonRepositoryImpl(
    private val api: CartoonApi
) : CartoonRepository, BaseRepository() {

    override fun getCharacters(): Flow<Either<String, List<Character>>> = doRequest(
        request = { api.getCharacters() },
        result = {it.characters.toDomain() }
    )

    override fun getCharacterById(id: Int?): Flow<Either<String, Character>> = doRequest(
        request = {api.getCharacterById(id) },
        result = {it}
    )

}