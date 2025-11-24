package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.data.models.CountDto
import com.example.rickandmortyapp.domain.models.Count
import com.example.rickandmortyapp.domain.models.TypeOfOperation

fun CountDto.mapToDomain(): Count {
    return Count(
        count = this.count ?: 0,
        typeOfOperation = TypeOfOperation.toOperation(this.typeOfOperation),
        createdAt = this.createdAt ?: 0L,
    )
}
