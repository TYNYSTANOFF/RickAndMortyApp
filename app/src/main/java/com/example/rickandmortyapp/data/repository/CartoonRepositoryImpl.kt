package com.example.rickandmortyapp.data.repository

import androidx.compose.foundation.pager.PagerScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.data.base.BaseRepository
import com.example.rickandmortyapp.data.datasource.CartoonApi
import com.example.rickandmortyapp.data.mapper.toDomain
import com.example.rickandmortyapp.data.paging.CharacterPagingSource
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import kotlinx.coroutines.flow.Flow

class CartoonRepositoryImpl(
    private val api: CartoonApi
) : CartoonRepository, BaseRepository() {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 15,
                initialLoadSize = 20 * 3,
                enablePlaceholders = false),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }).flow
    }


    override fun getCharacterById(id: Int?): Flow<Either<String, Character>> = doRequest(
        request = { api.getCharacterById(id) },
        result = { it }
    )

}