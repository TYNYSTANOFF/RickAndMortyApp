package com.example.rickandmortyapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface CartoonRepository {
    fun getCharacters(): Flow<List<String>>
}