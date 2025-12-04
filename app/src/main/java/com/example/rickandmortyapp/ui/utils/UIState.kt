package com.example.rickandmortyapp.ui.utils

sealed class UIState<out T>(
//    val data : T? = null,
//    val message: String? = "base value"
) {
    data object Empty : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T ) : UIState<T>()
    data class Error<T>(val message: String) : UIState<T>()
}