package com.example.rickandmortyapp.data.mapper

import com.example.rickandmortyapp.data.models.CharacterDto
import com.example.rickandmortyapp.data.models.LocationDto
import com.example.rickandmortyapp.data.models.OriginDto
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.models.Location
import com.example.rickandmortyapp.domain.models.Origin

fun CharacterDto?.toDomain() = Character(
    created = this?.created.orEmpty(),
    episode = this?.episode ?: emptyList(),
    gender = this?.gender.orEmpty(),
    id = this?.id ?: 0,
    image = this?.image.orEmpty(),
    location = this?.location.toDomain(),
    name = this?.name.orEmpty(),
    origin = this?.origin.toDomain(),
    species = this?.species.orEmpty(),
    status = this?.status.orEmpty(),
    type = this?.type.orEmpty(),
    url = this?.url.orEmpty()
)

fun List<CharacterDto>?.toDomain(): List<Character> {
    return this?.map { characterDto ->
        characterDto.toDomain()
    }?: emptyList()
}

fun LocationDto?.toDomain(): Location {
    return Location(
        name = this?.name.orEmpty(),
        url = this?.url.orEmpty()
    )
}

fun OriginDto?.toDomain(): Origin {
    return Origin(
        name = this?.name.orEmpty(),
        url = this?.url.orEmpty()
    )
}

