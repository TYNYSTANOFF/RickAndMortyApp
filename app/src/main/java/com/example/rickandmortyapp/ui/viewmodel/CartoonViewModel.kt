package com.example.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.usecases.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartoonViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _charactersState = MutableStateFlow<List<String>>(emptyList())
    val charactersState = _charactersState.asStateFlow()
//    val charactersState: StateFlow<List<String>> = _charactersState

    fun getCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.getCharacters().collect { data ->
                _charactersState.value = data
            }
        }
    }
}