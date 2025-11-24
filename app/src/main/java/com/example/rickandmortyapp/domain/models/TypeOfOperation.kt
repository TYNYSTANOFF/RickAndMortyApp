package com.example.rickandmortyapp.domain.models

enum class TypeOfOperation(
    val value: String
) {
    INCREMENT("increment"),
    DECREMENT("decrement"),
    RESET("reset"),
    NOTHING("nothing");

    companion object {
        fun toOperation(value: String?): TypeOfOperation {
            return TypeOfOperation.entries.firstOrNull { value == it.value } ?: NOTHING
        }
    }
}