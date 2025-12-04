package com.example.rickandmortyapp.ui.viewmodel

import android.provider.Contacts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.ui.utils.UIState
import com.example.rickandmortyapp.domain.models.Character
import com.example.rickandmortyapp.domain.usecases.GetCharacterByIdUseCase
//import com.example.rickandmortyapp.domain.usecases.GetCharactersUseCase
import com.example.rickandmortyapp.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel() {
    private val _charactersState = MutableStateFlow<UIState<Character?>>(UIState.Empty)
    val charactersState = _charactersState.asStateFlow()

    fun getCharacterById(id: Int?) {
        getCharacterByIdUseCase.getCharacterById(id).handleFlowData(_charactersState)
    }
}
