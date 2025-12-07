package com.example.rickandmortyapp.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CartoonRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    fun getCharacterById(id:Int?): Flow<Either<String, Character>>
}