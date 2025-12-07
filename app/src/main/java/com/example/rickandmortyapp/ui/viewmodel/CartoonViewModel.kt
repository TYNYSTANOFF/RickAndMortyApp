package com.example.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.ui.utils.UIState
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.repository.CartoonRepository
import com.example.rickandmortyapp.domain.usecases.GetCharactersUseCase
import com.example.rickandmortyapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CartoonViewModel(
    private val repository: CartoonRepository
) : BaseViewModel() {
    val charactersState = repository.getCharacters().cachedIn(viewModelScope)

}