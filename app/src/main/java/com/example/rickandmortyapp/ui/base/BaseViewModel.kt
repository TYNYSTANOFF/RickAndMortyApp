package com.example.rickandmortyapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.core.Either
import com.example.rickandmortyapp.ui.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Either<String, T>>.handleFlowData(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch {
            this@handleFlowData
                .onStart { state.value = UIState.Loading }
                .collect { result ->
                when (result) {
                    is Either.Left -> {
                        state.value = UIState.Error(result.value)
                    }

                    is Either.Right -> {
                        state.value = UIState.Success(result.value)
                    }
                }
            }
        }
    }
}
