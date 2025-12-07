package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val repository: CartoonRepository
) {
//    fun getCharacters(): Flow<Either<String, List<Character>>>
//    = repository.getCharacters()

}