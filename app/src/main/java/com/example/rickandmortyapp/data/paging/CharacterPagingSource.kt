package com.example.rickandmortyapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapp.data.datasource.CartoonApi
import com.example.rickandmortyapp.data.mapper.toDomain

const val START_INDEX = 1

class CharacterPagingSource(
    private val api: CartoonApi,
) : PagingSource<Int, com.example.rickandmortyapp.domain.models.Character>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.example.rickandmortyapp.domain.models.Character> {
        val currentPage = params.key ?: START_INDEX
        return try {
            val response = api.getCharacters(currentPage)
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()?.characters.toDomain(),
                    prevKey = if (currentPage != START_INDEX) currentPage - 1 else null,
                    nextKey = if (response.body()?.info?.next != null) currentPage + 1 else null,


                )
            } else {
                LoadResult.Error(Throwable("Не удалось загрузить список"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, com.example.rickandmortyapp.domain.models.Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}