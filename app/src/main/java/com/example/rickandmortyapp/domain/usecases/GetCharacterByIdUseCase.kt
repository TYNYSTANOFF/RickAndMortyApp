package com.example.rickandmortyapp.domain.usecases

import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterByIdUseCase(
    private val repository: CartoonRepository
) {
    fun getCharacterById(id : Int?): Flow<Either<String, Character>>
    = repository.getCharacterById(id)

}