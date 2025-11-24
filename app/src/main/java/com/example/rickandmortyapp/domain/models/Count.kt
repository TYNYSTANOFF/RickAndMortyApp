package com.example.rickandmortyapp.domain.models

data class Count(
    val count : Int,
    val typeOfOperation : TypeOfOperation,
    val createdAt : Long,
)
